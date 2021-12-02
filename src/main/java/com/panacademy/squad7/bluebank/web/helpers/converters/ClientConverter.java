package com.panacademy.squad7.bluebank.web.helpers.converters;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter {
    @Autowired
    AccountConverter accountConverter;

    public List<ClientResponse> toListOfResponse(List<Client> clients) {
        return clients.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .lastname(client.getLastname())
                .birthDate(client.getBirthDate())
                .motherName(client.getMotherName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .cellphone(client.getCellphone())
                .status(client.getStatus())
                .type(client.getType())
                .userId(client.getUser() != null ? client.getUser().getId() : null)
                .addressId(client.getAddress() != null ? client.getAddress().getId() : null)
                .accounts(
                        client.getAccounts() != null
                                ? accountConverter.toListOfResponse(client.getAccounts())
                                : null
                )
                .build();
    }

    public Client toModel(ClientRequest clientRequest) {
        return Client.builder()
                .name(clientRequest.getName())
                .lastname(clientRequest.getLastname())
                .birthDate(clientRequest.getBirthDate())
                .motherName(clientRequest.getMotherName())
                .email(clientRequest.getEmail())
                .phone(clientRequest.getPhone())
                .cellphone(clientRequest.getCellphone())
                .status(clientRequest.getStatus() != null ? clientRequest.getStatus() :  StatusType.A)
                .type(clientRequest.getType())
                .registration(clientRequest.getRegistration())
                .build();
    }
}
