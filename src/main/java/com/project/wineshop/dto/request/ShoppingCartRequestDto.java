package com.project.wineshop.dto.request;

import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.dto.response.UserResponseDto;
import lombok.Data;

import java.util.Map;

@Data
public class ShoppingCartRequestDto{
    private UserRequestDto user;
    private Map<ProductRequestDto, Integer> products;
}
