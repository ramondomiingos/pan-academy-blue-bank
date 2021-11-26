package com.panacademy.squad7.bluebank.web.helpers.converters;


import com.panacademy.squad7.bluebank.domain.enums.ClaimType;
import com.panacademy.squad7.bluebank.domain.enums.TransactionType;
import com.panacademy.squad7.bluebank.domain.models.Transaction;
import com.panacademy.squad7.bluebank.web.dtos.request.DepositRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.TransferRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.WithdrawRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionConverter {

    public List<TransactionResponse> toListOfResponse(List<Transaction> transactions) {
        return transactions.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TransactionResponse toResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .claim(transaction.getClaim().name())
                .type(transaction.getType().name())
                .originAccount(
                        transaction.getOriginAccount() != null
                                ? transaction.getOriginAccount().getAccountNumber()
                                : null
                )
                .destinationAccount(
                        transaction.getDestinationAccount() != null
                                ? transaction.getDestinationAccount().getAccountNumber()
                                : null
                )
                .amount(
                        transaction.getClaim().equals(ClaimType.C)
                                ? transaction.getAmount()
                                : transaction.getAmount().negate()
                )
                .createdAt(transaction.getCreatedAt())
                .build();
    }


    public Transaction toModel(DepositRequest depositRequest) {
        return Transaction.builder()
                .amount(depositRequest.getAmount())
                .claim(ClaimType.C)
                .type(TransactionType.DEPOSIT)
                .build();
    }

    public Transaction toModel(WithdrawRequest withdrawRequest) {
        return Transaction.builder()
                .amount(withdrawRequest.getAmount())
                .claim(ClaimType.D)
                .type(withdrawRequest.getType())
                .build();
    }

    public Transaction toModel(TransferRequest transferRequest) {
        return Transaction.builder()
                .amount(transferRequest.getAmount())
                .claim(ClaimType.D)
                .type(transferRequest.getType())
                .build();
    }
}
