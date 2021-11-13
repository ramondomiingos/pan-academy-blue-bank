package com.panacademy.squad7.bluebank.domain.enums;

public enum  AccountType {

    CC("Conta Corrente"),
    CP("Conta Poupança"),
    CS("Conta Salário");

    private String description;

    AccountType(String description){

        this.description = description;
    }

    public String getDescription() {

        return description;
    }

}
