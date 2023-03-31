package com.project.wineshop.dto.response;

import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.Role;
import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private ShippingDetails shippingDetails;
    private Set<Role> roles;
}
