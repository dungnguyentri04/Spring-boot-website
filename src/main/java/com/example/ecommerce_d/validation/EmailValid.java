package com.example.ecommerce_d.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

//Meta-Annotations
@Target({ElementType.TYPE, ElementType.FIELD,ElementType.ANNOTATION_TYPE})//pham vi ap dung
@Retention(RetentionPolicy.RUNTIME)//???muc do ton tai
@Constraint(validatedBy = EmailValidator.class)//logic xac thuc email( lop validator dung anotation)
@Documented

public @interface EmailValid {
    String message() default "Invalid email";
    Class<?>[] groups() default {};// mang cac lop kiem tra
    Class<? extends Payload>[] payload() default {};
}
