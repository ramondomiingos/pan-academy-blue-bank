package com.panacademy.squad7.bluebank.domain.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByAddressIgnoreCase(String address);
}