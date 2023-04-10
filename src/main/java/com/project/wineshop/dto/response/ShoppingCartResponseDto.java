package com.project.wineshop.dto.response;

import com.project.wineshop.model.Product;
import com.project.wineshop.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private UserResponseDto user;
    private Map<ProductResponseDto, Integer> products;
}
