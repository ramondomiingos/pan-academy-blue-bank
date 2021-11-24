package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.repositories.AccountsRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.services.AccountsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public Account create(Account account) {

        return accountsRepository.save(account);
    }

    @Override
    public Account update(Account account, Long id) {
        return accountsRepository.findById(id).map(a -> {
            account.setId(id);
            return accountsRepository.save(account);
        }).orElseThrow(() -> new ContentNotFoundException("account not found with id " + id));
    }

    @Override
    public void softDelete(Long id) {
        accountsRepository.findById(id).map(account -> {
            account.setStatus(StatusType.C);
            return accountsRepository.save(account);
        }).orElseThrow(() -> new ContentNotFoundException("account not found with id " + id));
    }

    @Override
    public void softBlock(Long id) {
        accountsRepository.findById(id).map(account -> {
            account.setStatus(StatusType.B);
            return accountsRepository.save(account);
        }).orElseThrow(() -> new ContentNotFoundException("account not found with id " + id));
    }

    @Override
    public Account findById(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("account not found with id " + id));
    }

    @Override
    public List<Account> findAll() {
        return accountsRepository.findAll()
                .stream()
                .filter( account -> account.getStatus().equals(StatusType.A))
                .collect(Collectors.toList());
    }
}
