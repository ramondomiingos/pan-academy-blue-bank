package com.panacademy.squad7.bluebank.shared.converters;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {

    public List<AddressResponse> toListOfResponse(List<Address> Addresses) {
        return Addresses.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .address(address.getAddress())
                .number(address.getAddressNumber())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

    public Address toModel(AddressRequest addressRequest) {
        Address address = Address.builder()
                .address(addressRequest.getAddress())
                .addressNumber(addressRequest.getNumber())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .build();

        if(addressRequest.getClientId() != null) {
            Client client = new Client();
            client.setId(addressRequest.getClientId());
            address.setClient(client);
        }

        return address;
    }
}
