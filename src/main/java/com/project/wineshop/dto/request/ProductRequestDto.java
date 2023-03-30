package com.project.wineshop.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String color;

    @NotNull
    private String type;

    @NotNull
    private Long manufacturerId;

    @NotNull
    private Boolean inStock;
}
