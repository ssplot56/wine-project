package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.response.ShoppingCartResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.service.mapper.ResponseDtoMapper;

import java.util.stream.Collectors;

public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCart, ShoppingCartResponseDto> {

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(shoppingCart.getId());
        shoppingCartResponseDto.setProductIds(shoppingCart.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
