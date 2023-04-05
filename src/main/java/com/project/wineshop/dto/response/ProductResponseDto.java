package com.project.wineshop.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponseDto {
    private Long id;

    private String name;

    private BigDecimal price;

    private String color;

    private String type;

    private String event;

    private String pairing;

    private List<String> pairWith;

    private Integer vintage;

    private String country;

    private String region;

    private String grape;

    private String taste;

    private String temperature;

    private byte[] image;
}
