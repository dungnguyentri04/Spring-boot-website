package com.example.ecommerce_d.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatchesValid {
    String message() default "Password don't matches";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
