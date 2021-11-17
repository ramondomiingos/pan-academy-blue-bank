package com.panacademy.squad7.bluebank.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Address not found with id " + id);
    }
}
