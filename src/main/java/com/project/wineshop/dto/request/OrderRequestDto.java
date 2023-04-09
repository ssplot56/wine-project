package com.project.wineshop.dto.request;

import com.project.wineshop.model.enums.OrderPayment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRequestDto {
    @NotNull
    @Size(min = 1)
    private Map<Long, Integer> products;
    @NotNull
    @Min(value = 1)
    private Long userId;
    @NotNull
    @NotBlank
    private String payment;
    @NotNull
    private Boolean isGift;
}
