package com.panacademy.squad7.bluebank.services.impl;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.repositories.AddressesRepository;
import com.panacademy.squad7.bluebank.exceptions.ContentNotFoundException;
import com.panacademy.squad7.bluebank.exceptions.InvalidInputException;
import com.panacademy.squad7.bluebank.services.AddressesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressesServiceImpl implements AddressesService {

    private final AddressesRepository addressesRepository;

    public AddressesServiceImpl(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @Override
    public Address create(Address address) {
        return addressesRepository.save(isValidAddress(address));
    }

    @Override
    public Address update(Address address, Long id) {
        return addressesRepository
                .findById(id).map(a -> {
                    address.setId(id);
                    return addressesRepository.save(isValidAddress(address));
                }).orElseThrow(() -> new ContentNotFoundException("address not found with id " + id));
    }

    @Override
    public void delete(Long id) {
        if (addressesRepository.existsById(id)) {
            addressesRepository.deleteById(id);
        } else {
            throw new ContentNotFoundException("address not found with id " + id);
        }
    }

    @Override
    public Address findById(Long id) {
        return addressesRepository
                .findById(id)
                .orElseThrow(() -> new ContentNotFoundException("address not found with id " + id));
    }

    @Override
    public List<Address> findAll() {
        return addressesRepository.findAll();
    }

    @Override
    public Address isValidAddress(Address address) {
        Long clientId = address.getClient().getId();
        System.out.println("isValid");
        Optional<Address> optionalAddress = addressesRepository.findByClientId(clientId);
        if (optionalAddress.isPresent()) {
            if (optionalAddress.get().getId().equals(address.getId())) {
                throw new InvalidInputException("client '" + clientId + "' already has address with id " + optionalAddress.get().getId());
            }
        }
        return address;
    }
}
