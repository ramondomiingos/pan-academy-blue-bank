package com.panacademy.squad7.bluebank.domain.enums;

public enum TransactionTypes {
    C("Credit"),
    D("Debit");

    private final String description;

    TransactionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
