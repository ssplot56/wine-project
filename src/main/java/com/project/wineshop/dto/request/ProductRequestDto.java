package com.project.wineshop.dto.request;

import jakarta.validation.constraints.Min;
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
    @Min(value = 0, message = "Рік розливу вина повинен бути додатнім числом")
    private Integer vintage;

    private String grape;

    private String taste;

    @NotNull
    private Long manufacturerId;

    @NotNull
    private Boolean inStock;
}
