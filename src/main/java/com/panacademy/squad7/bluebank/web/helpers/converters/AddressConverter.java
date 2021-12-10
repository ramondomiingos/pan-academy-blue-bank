package com.panacademy.squad7.bluebank.web.helpers.converters;

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
                .street(address.getStreet())
                .number(address.getAddressNumber())
                .details(address.getDetails())
                .neighborhood(address.getNeighborhood())
                .zip(address.getZip())
                .city(address.getCity())
                .state(address.getState())
                .clientId(address.getClient() != null ? address.getClient().getId() : null)
                .build();
    }

    public Address toModel(AddressRequest addressRequest) {
        return Address.builder()
                .street(addressRequest.getStreet())
                .addressNumber(addressRequest.getNumber())
                .details(addressRequest.getDetails())
                .neighborhood(addressRequest.getNeighborhood())
                .zip(addressRequest.getZip())
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
