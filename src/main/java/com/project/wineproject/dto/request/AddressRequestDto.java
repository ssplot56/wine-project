package com.project.wineproject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    private String city;
    private String street;
    private Integer houseNumber;
}
