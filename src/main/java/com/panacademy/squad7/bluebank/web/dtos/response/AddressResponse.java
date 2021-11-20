package com.panacademy.squad7.bluebank.web.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private Long id;

    private String address;

    private String number;

    private String city;

    private String state;

}
