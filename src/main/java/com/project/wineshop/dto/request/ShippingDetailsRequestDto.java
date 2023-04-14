package com.project.wineshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDetailsRequestDto {
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
}
