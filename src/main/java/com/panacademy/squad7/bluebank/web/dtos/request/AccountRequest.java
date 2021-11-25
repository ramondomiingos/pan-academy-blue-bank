package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.domain.models.Client;
import com.panacademy.squad7.bluebank.domain.models.Transaction;
import javax.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@Data
public class AccountRequest {

     @NotNull
    private Long agencyNumber;

     @NotNull
    private Long accountNumber;

    @NotNull
    private Character accountDigit;

    private BigDecimal balance;

    @NotNull
    @Schema(enumAsRef = true)
    private AccountType type;

    @Schema(enumAsRef = true)
    private StatusType status;

    @NotNull
    private Long clientId;

}
