package com.panacademy.squad7.bluebank.web.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.panacademy.squad7.bluebank.domain.enums.ClientType;
import com.panacademy.squad7.bluebank.domain.enums.StatusType;
import com.panacademy.squad7.bluebank.web.helpers.annotations.CpfCnpj;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ClientRequest {

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotBlank(message = "lastname must not be blank")
    private String lastname;

    @NotNull(message = "birthDate must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "motherName must not be blank")
    private String motherName;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be valid")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 6, max = 20, message = "password must be between 6 and 20 characters")
    private String password;

    @Size(min = 8, max = 11, message = "phone must be between 8 and 11 characters")
    private String phone;

    @Size(min = 9, max = 11, message = "cellphone must be between 9 and 11 characters")
    private String cellphone;

    @NotNull(message = "type must not be null")
    @Schema(enumAsRef = true)
    private ClientType type;

    @Hidden
    private StatusType status;

    @NotBlank(message = "registration must not be blank")
    @CpfCnpj
    private String registration;

}
