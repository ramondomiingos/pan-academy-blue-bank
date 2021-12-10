package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

    @Positive(message = "amount must not be negative")
    @DecimalMin(value = "0.01", message = "the amount value must be greater than 0.01")
    @NotNull(message = "amount must not be null")
    private BigDecimal amount;

    @Schema(type = "String", allowableValues = {"PAYMENT", "WITHDRAW", "DOC", "TED", "PIX"}, example = "WITHDRAW")
    @NotNull(message = "type must not be null")
    private TransactionType type;

}
