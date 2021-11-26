package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    @Positive
    @DecimalMin(value = "0.01", message = "the amount value must be greater than 0.01")
    @NotNull(message = "amount must not be null")
    private BigDecimal amount;

    @Schema(enumAsRef = true)
    @NotNull(message = "type must not be null")
    private TransactionType type;

}
