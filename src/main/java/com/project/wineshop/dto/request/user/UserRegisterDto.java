package com.project.wineshop.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Wrong format!")
    private String email;
    @NotEmpty(message = "The password couldn't be empty!")
    @Size(min = 8, message = "Password must be at least 8 symbols long.")
    private String password;
}
