package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccountRequest {

    @Max(value = 9999, message = "must be between 1 and 4 characters")
    @NotNull(message = "agencyNumber must not be null")
    private Long agencyNumber;

    @NotNull(message = "accountNumber must not be null")
    private Long accountNumber;

    @NotNull(message = "accountDigit must not be null")
    private Character accountDigit;

    private BigDecimal balance;

    @NotNull(message = "type must not be null")
    @Schema(enumAsRef = true)
    private AccountType type;

    @Schema(enumAsRef = true)
    private StatusType status;

    @NotNull(message = "clientId must not be null")
    private Long clientId;

}
