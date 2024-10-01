package com.example.ecommerce_d.validation;

import com.example.ecommerce_d.DTO.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatchesValid,Object> {
    @Override
    public void initialize(final PasswordMatchesValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        final UserDTO user = (UserDTO) obj;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
