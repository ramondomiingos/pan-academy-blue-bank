package com.panacademy.squad7.bluebank.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.panacademy.squad7.bluebank.services.ClientsService;
import com.panacademy.squad7.bluebank.shared.converters.ClientConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.ClientResponse;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ClientConverter clientConverter;

    @PostMapping
    public ResponseEntity<ClientResponse> create(@RequestBody ClientRequest client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConverter.toResponse(clientsService.create(clientConverter.toModel(client))));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientConverter.toListOfResponse(clientsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientConverter.toResponse(clientsService.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody ClientRequest client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConverter.toResponse(clientsService.update(clientConverter.toModel(client), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}