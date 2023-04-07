package com.project.wineshop.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestNewUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String region;
    private String city;
    private String deliveryService;
    private String wareHouse;
    private Long paymentId;
    private Boolean isGift;
    private Map<Long,Integer> products;
}
