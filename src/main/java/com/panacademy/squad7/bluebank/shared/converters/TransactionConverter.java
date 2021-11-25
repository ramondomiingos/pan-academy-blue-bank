package com.panacademy.squad7.bluebank.shared.converters;


import com.panacademy.squad7.bluebank.domain.enums.ClaimType;
import com.panacademy.squad7.bluebank.domain.models.Transaction;
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

    /*
    public Transaction toModel(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .agencyNumber(transactionRequest.getAgencyNumber())
                .transactionNumber(transactionRequest.getTransactionNumber())
                .transactionDigit(transactionRequest.getTransactionDigit())
                .balance(transactionRequest.getBalance())
                .type(transactionRequest.getType())
                .status(transactionRequest.getStatus())
                .client(
                        transactionRequest.getClientId() != null
                                ? Client.builder().id(transactionRequest.getClientId()).build()
                                : null
                )
                .build();
    }
    */
}
