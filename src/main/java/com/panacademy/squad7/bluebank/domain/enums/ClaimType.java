package com.panacademy.squad7.bluebank.domain.enums;

public enum ClaimType {
    C("Credit"),
    D("Debit");

    private final String description;

    ClaimType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
