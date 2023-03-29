package com.project.wineshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;
}
