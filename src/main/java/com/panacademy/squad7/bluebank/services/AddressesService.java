package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.Address;

import java.util.List;

public interface AddressesService {

    Address create(Address address);

    Address update(Address address, Long id);

    void delete(Long id);

    Address findById(Long id);

    List<Address> findAll();

    Address isValidAddress(Address address);
}
