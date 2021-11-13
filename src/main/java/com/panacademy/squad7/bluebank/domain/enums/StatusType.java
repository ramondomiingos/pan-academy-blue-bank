package com.panacademy.squad7.bluebank.domain.enums;

public enum StatusType {
    A("Ativa"),
    B("Bloqueada"),
    C("Cancelada");

    private String description;

    StatusType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
