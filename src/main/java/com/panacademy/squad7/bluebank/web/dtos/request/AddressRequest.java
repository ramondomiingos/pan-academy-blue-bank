package com.panacademy.squad7.bluebank.web.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    @NotBlank(message = "address must not be blank")
    private String address;

    private String number;

    @NotBlank(message = "city must not be blank")
    @Size(max = 30, message = "city must have a maximum of 30 characters")
    private String city;

    @NotBlank(message = "state must not be blank")
    @Size(min = 2, max = 2, message = "state must be 2 characters")
    private String state;

    @NotNull(message = "clientId must not be null")
    private Long clientId;

}

