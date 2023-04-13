package com.project.wineshop.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @NotBlank(message = "Field can't be blank!")
    @Email
    private String email;
    @NotBlank(message = "Password can't be blank!")
    private String password;
}
