package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.services.AddressesService;
import com.panacademy.squad7.bluebank.shared.conversors.AddressesConversor;
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

    //usar findById do ClientService e AddressService

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AddressesConversor addressesConversor;

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressesConversor.toAddressResponse(addressesService.create(addressesConversor.toAddressModel(address))));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAll() {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(addressesConversor.toAddressesResponse(addressesService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(addressesConversor.toAddressResponse(addressesService.findById(id)));
    }



    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id, @RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressesConversor.toAddressResponse(addressesService.update(addressesConversor.toAddressModel(address), id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressesService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
