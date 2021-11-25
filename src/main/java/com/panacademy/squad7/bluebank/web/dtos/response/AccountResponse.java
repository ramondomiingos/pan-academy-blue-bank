package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {

    private Long id;

    private Long agencyNumber;

    private Long accountNumber;

    private Character accountDigit;

    private BigDecimal balance;

    private AccountType type;

    private StatusType status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long clientId;

}
