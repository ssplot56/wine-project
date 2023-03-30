package com.project.wineshop.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> productIds;
}
