package com.panacademy.squad7.bluebank.web.controllers;

import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.services.ClientsService;
import com.panacademy.squad7.bluebank.services.UsersService;
import com.panacademy.squad7.bluebank.shared.converters.ClientConverter;
import com.panacademy.squad7.bluebank.shared.converters.UserConverter;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.ClientResponse;
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
@RequestMapping("/clients")
@Tag(name = "Clients", description = "endpoint for all client requests")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private UserConverter userConverter;

    @PostMapping
    @Operation(summary = "Add a new client", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content())
    })
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientsService.create(clientConverter.toModel(clientRequest));
        User user = userConverter.toModel(clientRequest);
        user.setClient(client);
        user = usersService.create(user);
        client.setUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConverter.toResponse(client));
    }

    @GetMapping
    @Operation(summary = "Find all clients", responses = {@ApiResponse(responseCode = "200", description = "Success")})
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientConverter.toListOfResponse(clientsService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find client by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Client Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the client for search")})
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clientConverter.toResponse(clientsService.findById(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a client", responses = {
            @ApiResponse(responseCode = "201", description = "Updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Client Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the client for search")})
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientRequest client) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientConverter.toResponse(clientsService.update(clientConverter.toModel(client), id)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Client Not Found", content = @Content())
    }, parameters = {@Parameter(description = "Id of the client for search")})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}