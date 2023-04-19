package com.project.wineshop.dto.request.user;

import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
    private String firstName;
    private String lastName;
    @Email(message = "Wrong format!")
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String region;
    private String city;
    private String deliveryService;
    private String warehouse;
    private String oldPassword;
    private String newPassword;
}
