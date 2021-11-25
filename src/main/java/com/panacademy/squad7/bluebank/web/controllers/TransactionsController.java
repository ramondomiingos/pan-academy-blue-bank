package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.TransactionsService;
import com.panacademy.squad7.bluebank.shared.converters.TransactionConverter;
import com.panacademy.squad7.bluebank.web.dtos.response.TransactionResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<TransactionResponse>> getAll(){
        return ResponseEntity.ok(transactionConverter.toListOfResponse(transactionsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getById(@RequestParam Long id){
        return ResponseEntity.ok(transactionConverter.toResponse(transactionsService.findById(id)));
    }
}
