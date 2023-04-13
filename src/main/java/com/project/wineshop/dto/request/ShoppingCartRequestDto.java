package com.project.wineshop.dto.request;

import com.project.wineshop.dto.request.user.UserRequestDto;
import lombok.Data;

import java.util.Map;

@Data
public class ShoppingCartRequestDto{
    private UserRequestDto user;
    private Map<ProductRequestDto, Integer> products;
}
