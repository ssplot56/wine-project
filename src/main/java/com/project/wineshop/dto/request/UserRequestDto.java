package com.project.wineshop.dto.request;

import com.project.wineshop.model.Address;
import com.project.wineshop.model.Card;
import com.project.wineshop.model.Role;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
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
    private List<Card> card;
    private Set<Role> roles;
}
