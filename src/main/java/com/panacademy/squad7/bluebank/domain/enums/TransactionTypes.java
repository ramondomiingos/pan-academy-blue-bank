package com.panacademy.squad7.bluebank.domain.enums;

import javax.persistence.Enumerated;

public enum TransactionTypes {
	C("Crédito"), D("Débito");
	
	private String description;
	
	TransactionTypes(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
	
	
	
}
