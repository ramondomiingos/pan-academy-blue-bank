package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private String street;

    private String number;

    private String details;

    private String neighborhood;

    private String zip;

    private String city;

    private String state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long clientId;

}
