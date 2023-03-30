package com.project.wineshop.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ManufacturerRequestDto {
    @NotNull(message = "Please provide a name of manufacturer")
    @Size(min = 3, max = 50, message
            = "Manufacturer name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Please provide a country")
    @Size(min = 3, max = 50, message
            = "Country name must be between 3 and 50 characters")
    private String country;

}
