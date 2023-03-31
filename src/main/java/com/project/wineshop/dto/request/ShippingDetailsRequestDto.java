package com.project.wineshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailsRequestDto {
    private String region;
    private String city;
    private String deliveryService;
    private String warehouse;
}
