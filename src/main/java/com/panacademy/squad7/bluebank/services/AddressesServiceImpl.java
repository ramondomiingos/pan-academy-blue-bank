package com.panacademy.squad7.bluebank.services;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.repositories.AddressesRepository;
import com.panacademy.squad7.bluebank.exceptions.AddressNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressesServiceImpl implements AddressesService {

    private final AddressesRepository addressesRepository;

    public AddressesServiceImpl(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @Override
    public Address create(Address address) {
        return addressesRepository.save(address);
    }

    @Override
    public Address update(Address address, Long id) {

        return addressesRepository.findById(id).map(a -> {
            address.setId(id);
            return addressesRepository.save(address);
        }).orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        if (addressesRepository.existsById(id)) {
            addressesRepository.deleteById(id);
        }
        throw new AddressNotFoundException(id);
    }

    @Override
    public Address findById(Long id) {
        return addressesRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public List<Address> findAll() {
        return addressesRepository.findAll();
    }
}
