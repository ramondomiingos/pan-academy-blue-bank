package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.services.AddressesService;
import com.panacademy.squad7.bluebank.shared.converters.AddressConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@Tag(name = "Addresses", description = "endpoint for all address requests")
public class AddressesController {

    @Autowired
    private AddressesService addressesService;

    @Autowired
    private AddressConverter addressConverter;

    @PostMapping
    @Operation(summary = "Add a new address", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Address Not Found", content = @Content())
    })
    public ResponseEntity<AddressResponse> create(@Valid @RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressConverter.toResponse(addressesService.create(addressConverter.toModel(address))));
    }

    @GetMapping
    @Operation(summary = "Find all addresses", responses = {@ApiResponse(responseCode = "200", description = "Success")})
    public ResponseEntity<List<AddressResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressConverter.toListOfResponse(addressesService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find address by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Address Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the address for search")})
    public ResponseEntity<AddressResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressConverter.toResponse(addressesService.findById(id)));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an address", responses = {
            @ApiResponse(responseCode = "201", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Address Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the address for search")})
    public ResponseEntity<AddressResponse> update(@PathVariable Long id, @Valid @RequestBody AddressRequest address) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressConverter.toResponse(addressesService.update(addressConverter.toModel(address), id)));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an address", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Address Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the address for search")})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressesService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
