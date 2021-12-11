package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.repositories.AccountsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.exceptions.InvalidInputException;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.ClientsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountsServiceImpl implements AccountsService {
    public static final String NOT_FOUND_MESSAGE = "account not found with id ";

    private final AccountsRepository accountsRepository;
    private final ClientsService clientsService;

    public AccountsServiceImpl(AccountsRepository accountsRepository, ClientsService clientsService) {
        this.accountsRepository = accountsRepository;
        this.clientsService = clientsService;
    }

    @Override
    public Account create(Account account) {
        return accountsRepository.save(isValidAccount(account));
    }

    @Override
    public Account update(Account account, Long id) {
        return accountsRepository.findById(id).map(a -> {
            a.setStatus(account.getStatus());
            return accountsRepository.save(a);
        }).orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public void softDelete(Long id) {
        Optional<Account> account = accountsRepository.findById(id);
        if (account.isPresent()) {
            Account a = account.get();
            a.setStatus(StatusType.C);
            accountsRepository.save(a);
        } else {
            throw new ContentNotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }

    @Override
    public Account findById(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<Account> findAll() {
        return accountsRepository.findAll().stream().filter(account -> account.getStatus().equals(StatusType.A))
                .collect(Collectors.toList());
    }

    @Override
    public Account findByAgencyNumberAndAccountNumberAndType(Long agencyNumber, Long accountNumber, AccountType type) {
        return accountsRepository.findByAgencyNumberAndAccountNumberAndType(agencyNumber, accountNumber, type)
                .orElseThrow(() -> new ContentNotFoundException(
                        "account not found in agency " + agencyNumber + " with account " + accountNumber + " for type " + type));
    }

    @Override
    public Long findMaxAccountNumberByAgencyNumberAndType(Long agencyNumber, AccountType type) {
        return accountsRepository.findMaxAccountNumberByAgencyNumberAndType(agencyNumber, type)
                .orElse(0L);
    }

    private char digitAccountCalculate(Long numberAccount) {
        String completeNumberAccount = String.format("%09d", numberAccount);
        int[] multipliers = {2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7};
        int number;
        int sum = 0;
        for (int i = 0; i < completeNumberAccount.length(); i++) {
            number = Character.getNumericValue(completeNumberAccount.charAt(i));
            sum = sum + (number * multipliers[i]);
        }
        int quotient = sum / 11;
        quotient = quotient * 11;
        int difference = sum - quotient;
        int digit = 11 - difference;
        if (digit > 9 || digit == 0) {
            return '1';
        } else {
            return Character.forDigit(digit, 10);
        }
    }

    private Account isValidAccount(Account account) {
        Long clientId = account.getClient().getId();
        Client client = clientsService.findById(clientId);

        List<Account> accounts = client.getAccounts();
        accounts.forEach(a -> {
            if (a.getAgencyNumber().equals(account.getAgencyNumber()) && a.getType().equals(account.getType())) {
                String message = "client '" + clientId + "' already has " + a.getType() + " account in agency " + a.getAgencyNumber();
                throw new InvalidInputException(message);
            }
        });

        account.setAccountNumber(findMaxAccountNumberByAgencyNumberAndType(account.getAgencyNumber(), account.getType()) + 1);
        account.setAccountDigit(digitAccountCalculate(account.getAccountNumber()));
        account.setClient(client);
        return account;
    }
}
