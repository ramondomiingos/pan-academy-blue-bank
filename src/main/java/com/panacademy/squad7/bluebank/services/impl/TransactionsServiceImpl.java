package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Transaction;
import com.panacademy.squad7.bluebank.domain.repositories.AccountsRepository;
import com.panacademy.squad7.bluebank.domain.repositories.TransactionsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.exceptions.InvalidInputException;
import com.panacademy.squad7.bluebank.services.TransactionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final AccountsRepository accountsRepository;

    public TransactionsServiceImpl(TransactionsRepository transactionsRepository, AccountsRepository accountsRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Transaction deposit(Transaction transaction) {
        Transaction transactionSaved = transactionsRepository.save(transaction);
        Account destinationAccount = transaction.getDestinationAccount();
        destinationAccount.setBalance(destinationAccount.getBalance().add(transaction.getAmount()));
        transactionSaved.setDestinationAccount(accountsRepository.save(destinationAccount));
        return transactionSaved;
    }

    @Override
    public Transaction withdraw(Transaction transaction) {
        Account originAccount = transaction.getOriginAccount();
        if (originAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new InvalidInputException("the account " + originAccount.getAccountNumber() + " does not have enough balance");
        }
        Transaction transactionSaved = transactionsRepository.save(transaction);
        originAccount.setBalance(originAccount.getBalance().subtract(transaction.getAmount()));
        transactionSaved.setOriginAccount(accountsRepository.save(originAccount));
        return transactionSaved;
    }

    @Override
    public Transaction transfer(Transaction transaction) {
        Account originAccount = transaction.getOriginAccount();
        Account destinationAccount = transaction.getDestinationAccount();
        if (originAccount.getBalance().compareTo(transaction.getAmount()) < 0) {
            throw new InvalidInputException("the origin account " + originAccount.getAccountNumber() + " does not have enough balance");
        }
        if (!destinationAccount.getStatus().equals(StatusType.A)) {
            throw new InvalidInputException("the destination account " + destinationAccount.getAccountNumber() + " is not active");
        }
        Transaction transactionSaved = transactionsRepository.save(transaction);
        originAccount.setBalance(originAccount.getBalance().subtract(transaction.getAmount()));
        destinationAccount.setBalance(destinationAccount.getBalance().add(transaction.getAmount()));
        transactionSaved.setOriginAccount(accountsRepository.save(originAccount));
        transactionSaved.setDestinationAccount(accountsRepository.save(destinationAccount));
        return transactionSaved;
    }

    @Override
    public Transaction findById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("transaction not found with id " + id));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionsRepository.findAll();
    }

}
