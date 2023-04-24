package com.project.wineshop.dto.response;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class ProductResponseDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private String color;

    private String type;

    private String event;

    private String pairing;

    private Set<String> dishes;

    private Integer vintage;

    private String country;

    private String region;

    private String grape;

    private String taste;

    private String temperature;

    private String imageLink;

    private Long popularity;
}
