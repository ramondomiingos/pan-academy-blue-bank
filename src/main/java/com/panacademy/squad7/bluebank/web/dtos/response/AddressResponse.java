package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long clientId;

}
