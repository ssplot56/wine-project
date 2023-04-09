package com.project.wineshop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestNewUserDto {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phoneNumber;
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
    private String wareHouse;
    @NotNull
    @NotBlank
    private String payment;
    @NotNull
    private Boolean isGift;
    @NotNull
    @Size(min = 1)
    private Map<Long,Integer> products;
}
