package com.panacademy.squad7.bluebank.web.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddressRequest {

    @NotBlank
    private String address;

    private String number;

    @NotBlank
    @Size(max = 30)
    private String city;

    @NotBlank
    @Size(min = 2, max = 2)
    private String state;

    @NotBlank
    private Long clientId;

}

