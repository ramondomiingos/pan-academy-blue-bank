package com.panacademy.squad7.bluebank.domain.enums;

public enum ClientType {
    NP("Natural Person"),
    LP("Legal Person");

    private final String description;

    ClientType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
