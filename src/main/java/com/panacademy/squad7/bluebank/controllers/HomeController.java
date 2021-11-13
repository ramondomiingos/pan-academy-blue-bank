package com.panacademy.squad7.bluebank.controllers;

import com.panacademy.squad7.bluebank.domain.models.Address;
import com.panacademy.squad7.bluebank.domain.repositories.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class HomeController {

    @GetMapping("/hello")
    public static String getHello() {
        return "Hello to Blue Bank API!";
    }

    @Autowired
    private AddressesRepository addressesRepository;

    @GetMapping
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok(addressesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Integer id) {
        Optional<Address> address = addressesRepository.findById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<Void> createAddress(@RequestBody Address address) {
        addressesRepository.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable Integer id, @RequestBody Address address) {
        Optional<Address> a = addressesRepository.findById(id);
        if (a.isPresent()) {
            address.setId(a.get().getId());
            addressesRepository.save(address);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
        Optional<Address> delAutor = addressesRepository.findById(id);
        if (delAutor.isPresent()) {
            addressesRepository.delete(delAutor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
