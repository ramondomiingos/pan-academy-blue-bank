package com.panacademy.squad7.bluebank.domain.enums;

public enum StatusType {
    A("Active"),
    B("Blocked"),
    C("Cancelled");

    private final String description;

    StatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
