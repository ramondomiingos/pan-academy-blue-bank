package com.panacademy.squad7.bluebank.web.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.shared.annotations.CpfCnpj;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ClientRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank
    private String motherName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 8, max = 11)
    private String phone;

    @Size(min = 9, max = 11)
    private String cellphone;

    @NotBlank
    @Schema(enumAsRef = true)
    private ClientType type;

    @NotBlank
    @CpfCnpj
    private String registration;

}
