package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Transaction;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.TransactionsService;
import com.panacademy.squad7.bluebank.shared.converters.TransactionConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.DepositRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.TransferRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.WithdrawRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.TransactionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction", description = "endpoint for all transactions requests")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private TransactionConverter transactionConverter;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(transactionConverter.toListOfResponse(transactionsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionConverter.toResponse(transactionsService.findById(id)));
    }

    @PostMapping("/deposit/{idAccount}")
    public ResponseEntity<TransactionResponse> deposit(@PathVariable Long idAccount,
                                                       @Valid @RequestBody DepositRequest depositRequest) {
        Transaction transaction = transactionConverter.toModel(depositRequest);
        transaction.setDestinationAccount(accountsService.findById(idAccount));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionConverter.toResponse(transactionsService.deposit(transaction)));
    }

    @PostMapping("/withdraw/{idAccount}")
    public ResponseEntity<TransactionResponse> withdraw(@PathVariable Long idAccount,
                                                        @Valid @RequestBody WithdrawRequest withdrawRequest) {
        Transaction transaction = transactionConverter.toModel(withdrawRequest);
        transaction.setOriginAccount(accountsService.findById(idAccount));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionConverter.toResponse(transactionsService.withdraw(transaction)));
    }

    @PostMapping("/transfer/{idAccount}")
    public ResponseEntity<TransactionResponse> transfer(@PathVariable Long idAccount,
                                                        @Valid @RequestBody TransferRequest transferRequest) {
        Transaction transaction = transactionConverter.toModel(transferRequest);
        transaction.setOriginAccount(accountsService.findById(idAccount));
        transaction.setDestinationAccount(accountsService
                .findByAgencyNumberAndAccountNumber(transferRequest.getAgencyNumber(), transferRequest.getAccountNumber())
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionConverter.toResponse(transactionsService.transfer(transaction)));
    }
}
