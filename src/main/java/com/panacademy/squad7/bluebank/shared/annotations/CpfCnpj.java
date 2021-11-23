package com.panacademy.squad7.bluebank.shared.annotations;

import com.panacademy.squad7.bluebank.shared.validators.CpfCnpjValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {CpfCnpjValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface CpfCnpj {

    String message() default "registration is not valid value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}