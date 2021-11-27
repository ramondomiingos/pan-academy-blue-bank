package com.panacademy.squad7.bluebank.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private Long id;

    private String claim;

    private String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long originAccount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long destinationAccount;

    private BigDecimal amount;

    private LocalDateTime createdAt;
}
