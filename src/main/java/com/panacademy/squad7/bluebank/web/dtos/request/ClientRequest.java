package com.panacademy.squad7.bluebank.web.dtos.request;

import java.time.LocalDate;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;


import lombok.Data;

@Data
public class ClientRequest {
	
	private String name;

    private String lastname;

    private LocalDate birthDate;

    private String motherName;

    private String email;

    private String phone;

    private String cellphone;

    private ClientType type;
    
    private String registration;

}
