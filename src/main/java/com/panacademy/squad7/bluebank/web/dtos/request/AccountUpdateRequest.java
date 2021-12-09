package com.panacademy.squad7.bluebank.web.dtos.request;

import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountUpdateRequest {
    @NotNull(message = "Status must not be null")
    @Schema(enumAsRef = true)
    private StatusType status;
}
