package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Account;
import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.services.AccountsService;
import com.panacademy.squad7.bluebank.services.AddressesService;
import com.panacademy.squad7.bluebank.services.ClientsService;
import com.panacademy.squad7.bluebank.shared.converters.AccountConverter;
import com.panacademy.squad7.bluebank.shared.converters.AddressConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.AccountRequest;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AccountResponse;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;
    @Autowired
    private ClientsService clientsService;
    @Autowired
    private AccountConverter accountConverter;

    @PostMapping
    public ResponseEntity<AccountResponse> create( @RequestBody AccountRequest accountRequest) {
        Client client = clientsService.findById(accountRequest.getClientId());
        Account account = accountConverter.toModel(accountRequest);
        account.setClient(client);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountConverter.toResponse(accountsService.create(account)));
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
