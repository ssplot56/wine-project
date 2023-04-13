package com.project.wineshop.dto.request;

import com.project.wineshop.model.enums.OrderPayment;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestDto {
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

    private Boolean createAccount;
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
    @NotNull
    @NotBlank
    private String payment;
    @NotNull
    private Boolean isGift;
    @NotNull
    @Size(min = 1)
    private Map<Long, Integer> products;
}
