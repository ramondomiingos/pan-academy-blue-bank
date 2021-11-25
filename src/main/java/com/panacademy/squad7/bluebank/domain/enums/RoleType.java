package com.panacademy.squad7.bluebank.domain.enums;

public enum RoleType {
    A("ROLE_ADMIN"),
    U("ROLE_USER");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
