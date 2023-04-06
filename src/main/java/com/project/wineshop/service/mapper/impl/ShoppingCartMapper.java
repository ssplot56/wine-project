package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.dto.response.ShoppingCartResponseDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCart, ShoppingCartResponseDto> {

    private final ResponseDtoMapper<User, UserResponseDto> userResponseDtoMapper;

    private final ResponseDtoMapper<Product, ProductResponseDto> productResponseDtoMapper;

    public ShoppingCartMapper(ResponseDtoMapper<User, UserResponseDto> responseDtoMapper,
                              ResponseDtoMapper<Product, ProductResponseDto> productResponseDtoMapper) {
        this.userResponseDtoMapper = responseDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setUser(userResponseDtoMapper.mapToDto(shoppingCart.getUser()));
        Map<ProductResponseDto, Integer> responseProducts = new HashMap<>();
        for(Map.Entry<Product, Integer> entrySet :shoppingCart.getProducts().entrySet()) {
            responseProducts.put(productResponseDtoMapper.mapToDto(entrySet.getKey()),entrySet.getValue());
        }
        shoppingCartResponseDto.setProducts(responseProducts);
        return shoppingCartResponseDto;
    }
}
