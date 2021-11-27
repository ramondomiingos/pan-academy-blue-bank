package com.panacademy.squad7.bluebank.domain.enums;

public enum AccountType {
    CA("Checking Account"),
    SA("Savings Account ");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
