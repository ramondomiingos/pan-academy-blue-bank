package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
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
        account = accountsRepository.save(account);
        account.setAccountDigit(digitAccountCalculate(account.getId()));
        return accountsRepository.save(account);
    }

    @Override
    public Account update(Account account, Long id) {
        return accountsRepository.findById(id).map(a -> {
            a.setStatus(account.getStatus());
            return accountsRepository.save(a);
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
    public Account findById(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("account not found with id " + id));
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
        return accountsRepository.findMaxAccountNumberByAgencyNumberAndType(agencyNumber,type)
                .orElse(0L);
    }

    public char digitAccountCalculate( Long numberAccount){
        String completeNumberAccount = String.format("%09d", numberAccount);
        int[] multipliers = {2,9,8,7,6,5,4,3,2,9,8,7};
        int number;
        int sum = 0;
        for (int i = 0; i < completeNumberAccount.length(); i++){
            number  = Character.getNumericValue(completeNumberAccount.charAt(i));
            sum = sum+ (number * multipliers[i]);
        }
        int quotient = sum /11;
        quotient = quotient * 11;
        int difference = sum - quotient;
        int digit = 11 - difference;
        if(digit  > 9 || digit == 0 ){
             return '1';
        }else{
            return Character.forDigit( digit, 10);
        }
    }
}
