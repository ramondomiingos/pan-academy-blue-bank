package com.panacademy.squad7.bluebank.web.dtos.response;

import java.time.LocalDate;
import java.util.List;

import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.models.Account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {
	
	private Long id;
		
    private String name;

    private String lastname;

    private LocalDate birthDate;

    private String motherName;

    private String email;

    private String phone;

    private String cellphone;

    private ClientType type;

    private Long addressId;
    	
    private Long userId;

    private List<Account> accounts;

}
