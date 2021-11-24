package com.panacademy.squad7.bluebank.shared.converters;


import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AccountResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter {



    public List<AccountResponse> toListOfResponse(List<Account> accounts ) {
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
                .build();
    }

    public Account toModel(AccountRequest accountRequest) {
        Account account =  Account.builder()
                .agencyNumber(accountRequest.getAgencyNumber())
                .accountNumber(accountRequest.getAccountNumber())
                .accountDigit(accountRequest.getAccountDigit())
                .balance(accountRequest.getBalance())
                .type(accountRequest.getType())
                .status(accountRequest.getStatus())
                .build();

        if(accountRequest.getClientId() != null){
            Client client = new Client();
            client.setId(accountRequest.getClientId());
            account.setClient(client);

        }
        return account;
    }

}
