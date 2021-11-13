package com.panacademy.squad7.bluebank.dao;

import com.panacademy.squad7.bluebank.domain.models.Address;

import java.util.List;

public interface AddressDao {
    void save(Address address);

    void update(Address address);

    void delete(Long id);

    Address findById(Long id);

    List<Address> findAll();
}
