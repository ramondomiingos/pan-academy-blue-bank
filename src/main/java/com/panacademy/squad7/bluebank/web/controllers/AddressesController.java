package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.services.AddressesService;
import com.panacademy.squad7.bluebank.shared.converters.AddressConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AddressConverter addressConverter;

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressConverter.toResponse(addressesService.create(addressConverter.toModel(address))));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressConverter.toListOfResponse(addressesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressConverter.toResponse(addressesService.findById(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id, @RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressConverter.toResponse(addressesService.update(addressConverter.toModel(address), id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressesService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
