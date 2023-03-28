package com.project.wineshop.dto.response;

import com.project.wineshop.model.Address;
import com.project.wineshop.model.Card;
import com.project.wineshop.model.ShoppingCart;
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
    private Address address;
    private ShoppingCart shoppingCart;
    private Card card;
    private Set<Role> roles;
}
