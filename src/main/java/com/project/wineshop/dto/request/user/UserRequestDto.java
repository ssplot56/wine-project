package com.project.wineshop.dto.request.user;

import com.project.wineshop.dto.request.ShippingDetailsRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    @NotBlank
    private String email;

    private String password;
    @NotBlank
    private String phoneNumber;
    private LocalDate birthDate;
    @NotNull
    @Valid
    private ShippingDetailsRequestDto shippingDetailsRequest;

}
