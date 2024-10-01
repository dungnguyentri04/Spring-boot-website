package com.example.ecommerce_d.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValid,String> {
    @Override
    public void initialize(PasswordValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String MIN_LENGTH = "8";
        String MAX_LENGTH = "20";
        boolean SPECIAL_CHAR_NEEDED = false;

        String ONE_DIGIT = "(?=.*[0-9])";
        String LOWER_CASE = "(?=.*[a-z])";
        String UPPER_CASE = "(?=.*[A-Z])";
        String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
        String NO_SPACE = "(?=\\S+$)";

        String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
        String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;
        boolean isValid = password.matches(PATTERN);
        if (!isValid) {
            // Nếu không hợp lệ, có thể tùy chỉnh thông báo lỗi
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password must be between " + MIN_LENGTH + " and " + MAX_LENGTH + " characters, contain at least one digit, one lowercase letter, one uppercase letter, and no whitespace.")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
