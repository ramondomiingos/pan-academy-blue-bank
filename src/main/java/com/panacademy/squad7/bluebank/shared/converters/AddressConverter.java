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

    public List<AddressResponse> toListOfResponse(List<Address> addresses) {
        return addresses.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .address(address.getAddress())
                .number(address.getAddressNumber())
                .city(address.getCity())
                .state(address.getState())
                .clientId(address.getClient() != null ? address.getClient().getId() : null)
                .build();
    }

    public Address toModel(AddressRequest addressRequest) {
        return Address.builder()
                .address(addressRequest.getAddress())
                .addressNumber(addressRequest.getNumber())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .client(
                        addressRequest.getClientId() != null
                                ? Client.builder().id(addressRequest.getClientId()).build()
                                : null
                )
                .build();
    }
}
