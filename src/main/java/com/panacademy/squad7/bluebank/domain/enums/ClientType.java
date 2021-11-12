package com.panacademy.squad7.bluebank.domain.enums;

public enum ClientType {
    PF("Pessoa Física"), PJ("Pessoa Jurídica");

    private String description;

    ClientType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
