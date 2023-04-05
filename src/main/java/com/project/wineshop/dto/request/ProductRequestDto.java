package com.project.wineshop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {
    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String type;

    @NotNull
    private String color;

    @NotNull(message = "Please provide an event")
    @Size(min = 4, max = 255, message = "Event must be between 4 and 255 characters")
    private String event;

    @NotNull(message = "Please provide a pairing")
    @Size(min = 4, max = 200, message = "Pairing must be between 4 and 255 characters")
    private String pairing;

    @NotNull(message = "Please provide a vintage")
    @Min(value = 0, message = "Vintage must be greater than 0")
    private Integer vintage;

    private String country;

    private String region;

    private String grape;

    private String taste;

    private String temperature;

    private byte[] image;
}
