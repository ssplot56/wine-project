package com.project.wineshop.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @NotNull
    @Size(min=8)
    private String password;
    @NotNull
    @NotBlank
    private String phoneNumber;
    private LocalDate birthDate;
    @NotNull
    @NotBlank
    private String region;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String deliveryService;
    @NotNull
    @NotBlank
    private String warehouse;
}
