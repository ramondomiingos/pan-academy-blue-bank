package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AccountRequestUpdate {


    @NotNull(message = "Status must not be null")
    @Schema(enumAsRef = true)
    private StatusType status;



}
