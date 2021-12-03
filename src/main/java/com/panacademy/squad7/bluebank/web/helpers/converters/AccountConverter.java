package com.panacademy.squad7.bluebank.web.helpers.converters;


import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter {

    @Autowired
    TransactionConverter transactionConverter;

    public List<AccountResponse> toListOfResponse(List<Account> accounts) {
        return accounts.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .agencyNumber(account.getAgencyNumber())
                .accountNumber(account.getAccountNumber())
                .accountDigit(account.getAccountDigit())
                .balance(account.getBalance())
                .type(account.getType())
                .status(account.getStatus())
                .clientId(account.getClient() != null ? account.getClient().getId() : null)
                .build();
    }

    public AccountResponse toExtractResponse(Account account) {
        AccountResponse accountResponse = toResponse(account);
        accountResponse.setMadeTransfers(transactionConverter.toListOfResponse(account.getMadeTransfers()));
        accountResponse.setReceivedTransfers(transactionConverter.toListOfResponse(account.getReceivedTransfers()));
        return accountResponse;
    }

    public Account toModel(AccountRequest accountRequest) {
        return Account.builder()
                .agencyNumber(accountRequest.getAgencyNumber())
                .accountNumber(accountRequest.getAccountNumber()!= null ? accountRequest.getAccountNumber(): null)
                .accountDigit(accountRequest.getAccountDigit()!= null ? accountRequest.getAccountDigit(): '0')
                .balance(accountRequest.getBalance()!= null ? accountRequest.getBalance(): BigDecimal.valueOf(0))
                .type(accountRequest.getType())
                .status(accountRequest.getStatus()!= null ? accountRequest.getStatus(): StatusType.A)
                .client(
                        accountRequest.getClientId() != null
                                ? Client.builder().id(accountRequest.getClientId()).build()
                                : null
                )
                .build();
    }

}
