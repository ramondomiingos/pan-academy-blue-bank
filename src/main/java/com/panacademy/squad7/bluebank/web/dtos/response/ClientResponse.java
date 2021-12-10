package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ClientResponse {

    private Long id;

    private String name;

    private String lastname;

    private LocalDate birthDate;

    private String motherName;

    private String email;

    private String phone;

    private String cellphone;

    private StatusType status;

    private ClientType type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long addressId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AccountResponse> accounts;

}
