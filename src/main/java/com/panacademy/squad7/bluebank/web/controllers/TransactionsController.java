package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Transaction;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.TransactionsService;
import com.panacademy.squad7.bluebank.web.helpers.converters.TransactionConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.DepositRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.TransferRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.WithdrawRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "endpoint for all transactions requests")
@SecurityRequirement(name = "bearerAuth")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private TransactionConverter transactionConverter;

    @GetMapping
    @Operation(summary = "Find all transactions", responses = {@ApiResponse(responseCode = "200", description = "Success")})
    public ResponseEntity<List<TransactionResponse>> getAll() {
        return ResponseEntity.ok(transactionConverter.toListOfResponse(transactionsService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find transaction by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Transaction Not Found", content = @Content())
    }, parameters = {@Parameter(name = "id", description = "Id of the transaction for search")})
    public ResponseEntity<TransactionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionConverter.toResponse(transactionsService.findById(id)));
    }

    @PostMapping("/deposit")
    @Operation(summary = "Deposit an amount into an account", responses = {
            @ApiResponse(responseCode = "201", description = "Deposited"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Account Not Found", content = @Content())
    })
    public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody DepositRequest depositRequest) {
        Transaction transaction = transactionConverter.toModel(depositRequest);
        transaction.setDestinationAccount(accountsService
                .findByAgencyNumberAndAccountNumber(depositRequest.getAgencyNumber(), depositRequest.getAccountNumber())
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionConverter.toResponse(transactionsService.deposit(transaction)));
    }

    @PostMapping("/withdraw/{idAccount}")
    @Operation(summary = "Withdraw an amount from an account", responses = {
            @ApiResponse(responseCode = "201", description = "Draw"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Account Not Found", content = @Content())
    }, parameters = {@Parameter(name = "idAccount", description = "withdrawal origin account Id")})
    public ResponseEntity<TransactionResponse> withdraw(@PathVariable Long idAccount,
                                                        @Valid @RequestBody WithdrawRequest withdrawRequest) {
        Transaction transaction = transactionConverter.toModel(withdrawRequest);
        transaction.setOriginAccount(accountsService.findById(idAccount));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionConverter.toResponse(transactionsService.withdraw(transaction)));
    }

    @PostMapping("/transfer/{idAccount}")
    @Operation(summary = "Transfer an amount from one account to another", responses = {
            @ApiResponse(responseCode = "201", description = "Transferred"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Account Not Found", content = @Content())
    }, parameters = {@Parameter(name = "idAccount", description = "transfer origin account Id")})
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
