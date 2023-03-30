package com.project.wineshop.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private String cardNumber;
    @Size(min = 2, max = 2)
    private String expiredMonth;
    @Size(min = 2, max = 2)
    private String expiredYear;
    @Size(min = 3, max = 3)
    private String cvv;
}
