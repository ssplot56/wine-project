package com.project.wineproject.dto.request;

import com.project.wineproject.model.Address;
import com.project.wineproject.model.Card;
import com.project.wineproject.model.ShoppingCart;
import com.project.wineproject.model.Role;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;
    private Address address;
    private Card card;
    private Set<Role> roles;
}
