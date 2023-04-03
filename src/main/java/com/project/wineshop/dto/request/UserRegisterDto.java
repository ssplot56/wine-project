package com.project.wineshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
