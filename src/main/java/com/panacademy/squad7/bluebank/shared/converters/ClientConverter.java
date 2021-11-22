package com.panacademy.squad7.bluebank.shared.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.ClientResponse;

@Component
public class ClientConverter {

	public List<ClientResponse> toListOfResponse(List<Client> clients) {
		return clients.stream().map(this::toResponse).collect(Collectors.toList());
	}

	public ClientResponse toResponse(Client client) {
		ClientResponse clientResponse = ClientResponse.builder()
				.id(client.getId())
				.name(client.getName())
				.lastname(client.getLastname())
				.birthDate(client.getBirthDate())
				.motherName(client.getMotherName())
				.email(client.getEmail())
				.phone(client.getPhone())
				.cellphone(client.getCellphone())
				.type(client.getType())
				.accounts(client.getAccounts())
				.build();
		
		if (client.getAddress() != null) {
			clientResponse.setAddressId(client.getAddress().getId());
		}
		
		if (client.getUser() != null) {
			clientResponse.setUserId(client.getUser().getId());
		}
		
		return clientResponse;
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
		.type(clientRequest.getType())
		.registration(clientRequest.getRegistration())
		.build();
	}
}
