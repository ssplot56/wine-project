package com.project.wineshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShippingDetailsRequestDto {
    @NotBlank
    private String region;
    @NotBlank
    private String city;
    @NotBlank
    private String deliveryService;
    @NotBlank
    private String warehouse;
}
