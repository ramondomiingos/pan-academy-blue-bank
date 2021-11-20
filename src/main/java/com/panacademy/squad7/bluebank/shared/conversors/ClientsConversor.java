package com.panacademy.squad7.bluebank.shared.conversors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.models.User;
import com.panacademy.squad7.bluebank.web.dtos.request.ClientRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.ClientResponse;

@Component
public class ClientsConversor {

	public List<ClientResponse> toClientsResponse(List<Client> clients) {
		return clients.stream().map(this::toClientResponse).collect(Collectors.toList());
	}

	public ClientResponse toClientResponse(Client client) {
		ClientResponse cr = ClientResponse.builder()
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
			cr.setAddressId(client.getAddress().getId());
		}
		
		if (client.getUser() != null) {
			cr.setUserId(client.getUser().getId());
		}
		
		return cr;
	}

	public Client toClientModel(ClientRequest clientRequest) {
		Client client = Client.builder()
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
		
		
		if(clientRequest.getAddressId() != null) {
			Address address = new Address();
			address.setId(clientRequest.getAddressId());
			client.setAddress(address);
		}
		
		if(clientRequest.getUserId() != null) {
			User user = new User();
			user.setId(clientRequest.getUserId());
			client.setUser(user);
		}
		
		return client;
	}
}
