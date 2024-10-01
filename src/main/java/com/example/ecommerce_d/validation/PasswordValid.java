package com.example.ecommerce_d.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface PasswordValid {
    String message() default "Invalid password";
    Class<?>[] groups() default {};// mang cac lop kiem tra
    Class<? extends Payload>[] payload() default {};
}
