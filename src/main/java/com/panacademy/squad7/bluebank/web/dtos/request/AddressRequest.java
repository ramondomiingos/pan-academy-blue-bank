package com.panacademy.squad7.bluebank.web.dtos.request;

import lombok.Data;

@Data
public class AddressRequest {

    private String address;

    private String number;

    private String city;

    private String state;

    private Long clientId;

}

