package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.AddressesService;
import com.panacademy.squad7.bluebank.shared.converters.AccountConverter;
import com.panacademy.squad7.bluebank.shared.converters.AddressConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AccountResponse;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/accounts")

public class AccountsController {

    @Autowired
    private AccountsService accountsService;
    //Todo: refactor to requestbody
    @Autowired
    private AccountConverter accountConverter;
    //@Operation( summary = "Perform operation of creating a bank account.",  description = " ")
    @PostMapping
    @Parameter(name ="agencyNumber",  description = "number of agency" , schema = @Schema(type = "integer"))
    @Parameter(name ="accountNumber", description = "Number of account", schema = @Schema(type = "integer"))
    @Parameter(name ="accountDigit",description = "digit validator of account number", schema = @Schema(type = "string"))
    @Parameter(name ="balance",description = "balance Initial", schema = @Schema(type = "number"))
    @Parameter(name ="type",description = "type of accont,CC(\"Conta Corrente\"),CP(\"Conta Poupança\"),CS(\"Conta Salário\") ", schema = @Schema(type = "string",  allowableValues = {"CC","CP","CS"}))
    @Parameter(name ="status", description = "status of account,A (\"Active\"),B (\"Blocked\"),C(\"Cancelled\"); ",schema = @Schema(type = "string",  allowableValues = {"A","B","C"}))
    /**
* @param agencyNumber  number of agency
* @param accountNumber Number of account
* @param accountDigit digit validator of account number
* @param balance balance Initial
* @param type type of accont: CC("Conta Corrente"),CP("Conta Poupança"),CS("Conta Salário")
* @param status status of account: A ("Active"),B ("Blocked"),C("Cancelled");
* @return account
*/

    public ResponseEntity<AccountResponse> create(@RequestBody AccountRequest account) {
        Account teste = accountsService.create(accountConverter.toModel(account));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountConverter.toResponse(accountsService.create(accountConverter.toModel(account))));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountConverter.toListOfResponse(accountsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountConverter.toResponse(accountsService.findById(id)));
    }

}
