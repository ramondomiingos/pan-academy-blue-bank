package com.panacademy.squad7.bluebank.shared.conversors;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.web.dtos.request.AddressRequest;
import com.panacademy.squad7.bluebank.web.dtos.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressesConversor {

    public List<AddressResponse> toAddressesResponse(List<Address> Addresses) {
        return Addresses.stream().map(this::toAddressResponse).collect(Collectors.toList());
    }

    public AddressResponse toAddressResponse (Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .address(address.getAddress())
                .number(address.getNumber())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

    public Address toAddressModel(AddressRequest addressRequest) {
        Address address = Address.builder()
                .address(addressRequest.getAddress())
                .number(addressRequest.getNumber())
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
