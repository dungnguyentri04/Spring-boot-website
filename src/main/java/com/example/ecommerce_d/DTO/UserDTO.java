package com.example.ecommerce_d.DTO;

import com.example.ecommerce_d.validation.EmailValid;
import com.example.ecommerce_d.validation.PasswordMatchesValid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@PasswordMatchesValid
public class UserDTO {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private  String lastName;

    @NotNull
    @NotEmpty
    private String password;
    private String passwordConfirm;

    @EmailValid
    @NotNull
    @NotEmpty
    private String email;


}
