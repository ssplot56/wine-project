package com.project.wineshop.dto.response;

import com.project.wineshop.model.Manufacturer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private String color;

    private String type;

    private Integer vintage;

    private Manufacturer manufacturer;

    private String pairing;

    private String grape;

    private String taste;

    private Byte temperature;
}
