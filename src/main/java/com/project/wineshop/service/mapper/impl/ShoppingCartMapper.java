package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.request.ShoppingCartRequestDto;
import com.project.wineshop.dto.request.user.UserRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.dto.response.ShoppingCartResponseDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCart, ShoppingCartResponseDto>,
        RequestDtoMapper<ShoppingCart, ShoppingCartRequestDto> {

    private final ResponseDtoMapper<User, UserResponseDto> userResponseDtoMapper;

    private final ResponseDtoMapper<Product, ProductResponseDto> productResponseDtoMapper;

    private final RequestDtoMapper<User, UserRequestDto> userRequestDtoMapper;

    private final RequestDtoMapper<Product, ProductRequestDto> productRequestDtoMapper;

    public ShoppingCartMapper(ResponseDtoMapper<User, UserResponseDto> responseDtoMapper,
                              ResponseDtoMapper<Product, ProductResponseDto> productResponseDtoMapper,
                              RequestDtoMapper<User, UserRequestDto> userRequestDtoMapper,
                              RequestDtoMapper<Product, ProductRequestDto> productRequestDtoMapper1) {
        this.userResponseDtoMapper = responseDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
        this.userRequestDtoMapper = userRequestDtoMapper;
        this.productRequestDtoMapper = productRequestDtoMapper1;
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

    @Override
    public ShoppingCart mapToModel(ShoppingCartRequestDto shoppingCartRequestDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(userRequestDtoMapper.mapToModel(shoppingCartRequestDto.getUser()));
        Map<Product, Integer> products = new HashMap<>();
        for(Map.Entry<ProductRequestDto, Integer> entrySet :shoppingCartRequestDto.getProducts().entrySet()) {
            products.put(productRequestDtoMapper.mapToModel(entrySet.getKey()), entrySet.getValue());
        }
        shoppingCart.setProducts(products);
        return shoppingCart;
    }
}
