package com.panacademy.squad7.bluebank.web.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class DepositRequest {

    @Min(value = 1, message = "agencyNumber must be different from 0")
    @Digits(integer = 4, fraction = 0, message = "agencyNumber must be between 1 and 4 characters")
    @NotNull(message = "agencyNumber must not be null")
    private Long agencyNumber;

    @Min(value = 1, message = "accountNumber must be different from 0")
    @NotNull(message = "accountNumber must not be null")
    private Long accountNumber;

    @Schema(example = "0")
    @NotNull(message = "accountDigit must not be null")
    private Character accountDigit;

    @Positive(message = "amount must not be negative")
    @DecimalMin(value = "0.01", message = "the amount value must be greater than 0.01")
    @NotNull(message = "amount must not be null")
    private BigDecimal amount;

}
