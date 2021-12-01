package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.ClientsService;
import com.panacademy.squad7.bluebank.web.helpers.converters.AccountConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AccountResponse;
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
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "endpoint for all accounts requests")
@SecurityRequirement(name = "bearerAuth")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private AccountConverter accountConverter;

    @PostMapping
    @Operation(summary = "Add a new account", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Client Not Found", content = @Content())
    })
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = accountConverter.toModel(accountRequest);
        account.setClient(clientsService.findById(accountRequest.getClientId()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountConverter.toResponse(accountsService.create(account)));
    }

    @GetMapping
    @Operation(summary = "Find all accounts", responses = {@ApiResponse(responseCode = "200", description = "Success")})
    public ResponseEntity<List<AccountResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountConverter.toListOfResponse(accountsService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find account by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Account Not Found", content = @Content())
    }, parameters = {@Parameter(name = "id", description = "Id of the account for search")})
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountConverter.toResponse(accountsService.findById(id)));
    }

    @GetMapping("/{id}/extract")
    @Operation(summary = "Find account by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Account Not Found", content = @Content())
    }, parameters = {@Parameter(name = "id", description = "Id of the account for search")})
    public ResponseEntity<AccountResponse> getExtractById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountConverter.toExtractResponse(accountsService.findById(id)));
    }

}
