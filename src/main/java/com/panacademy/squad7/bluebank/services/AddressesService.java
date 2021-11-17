package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AddressesService {

    Address  create(Address address);

    Address update(Address address, Integer id);

    void  delete(Integer id);

    Address findById(Integer id);

    List<Address> findAll();
}
