package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.models.Transaction;

import javax.persistence.*;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
@Data
public class AccountRequest {

    @NotBlank
    private Long agencyNumber;
    @NotBlank
    private Long accountNumber;
    @NotBlank
    private Character accountDigit;

    //@Column(nullable = false, columnDefinition = "decimal(10, 2) default 0")

    private BigDecimal balance;

    @NotBlank
    @Schema(enumAsRef = true)
    private AccountType type;

    @Schema(enumAsRef = true)
    private StatusType status;

    @NotBlank
    private Long clientId;




}
