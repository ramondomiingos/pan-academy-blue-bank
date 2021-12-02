package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.panacademy.squad7.bluebank.domain.enums.AccountType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TransactionResponse> receivedTransfers;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TransactionResponse> madeTransfers;

}
