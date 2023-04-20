package com.project.wineshop.dto.request;

import com.project.wineshop.model.enums.ProductColor;
import com.project.wineshop.model.enums.ProductEvent;
import com.project.wineshop.model.enums.ProductType;
import com.project.wineshop.validation.EnumValue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @EnumValue(enumClass = ProductType.Type.class)
    private String type;

    @NotNull
    @EnumValue(enumClass = ProductColor.Color.class)
    private String color;

    @NotNull(message = "Please provide an event")
    @EnumValue(enumClass = ProductEvent.Event.class)
    private String event;

    @NotNull(message = "Please provide a pairing")
    @Size(min = 4, max = 200, message = "Pairing must be between 4 and 200 characters")
    private String pairing;

    private List<String> dishes;

    @NotNull(message = "Please provide a vintage")
    @Min(value = 0, message = "Vintage must be greater than 0")
    private Integer vintage;

    @NotBlank
    private String country;

    private String region;

    private String grape;

    private String taste;

    private String temperature;

    @Size(min = 4, max = 2000, message = "imageLink must be between 4 and 2000 characters")
    private String imageLink;
}
