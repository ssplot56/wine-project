package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.WineDishPairing;
import com.project.wineshop.model.enums.ProductColor;
import com.project.wineshop.model.enums.ProductEvent;
import com.project.wineshop.model.enums.ProductType;
import com.project.wineshop.service.DishService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper implements
        RequestDtoMapper<Product, ProductRequestDto>,
        ResponseDtoMapper<Product, ProductResponseDto> {
    private final DishService dishService;


    public ProductMapper(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setType(ProductType.Type.valueOf(productRequestDto.getType()));
        product.setColor(ProductColor.Color.valueOf(productRequestDto.getColor()));
        product.setEvent(ProductEvent.Event.valueOf(productRequestDto.getEvent()));
        product.setPairing(productRequestDto.getPairing());
        product.setVintage(productRequestDto.getVintage());
        product.setCountry(productRequestDto.getCountry());
        product.setRegion(productRequestDto.getRegion());
        product.setGrape(productRequestDto.getGrape());
        product.setTaste(productRequestDto.getTaste());
        product.setTemperature(productRequestDto.getTemperature());
        product.setImage(productRequestDto.getImage());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setColor(product.getColor().name());
        responseDto.setType(product.getType().name());
        responseDto.setEvent(product.getEvent().name());
        responseDto.setPairing(product.getPairing());
        List<String> dishNames = new ArrayList<>();
        List<WineDishPairing> wineDishPairings = product.getWineDishPairings();
        for (WineDishPairing wineDishPairing : wineDishPairings) {
            dishNames.add(wineDishPairing.getDish().getName());
        }
        responseDto.setPairWith(dishNames);
        responseDto.setVintage(product.getVintage());
        responseDto.setCountry(product.getCountry());
        responseDto.setRegion(product.getRegion());
        responseDto.setGrape(product.getGrape());
        responseDto.setTaste(product.getTaste());
        responseDto.setTemperature(product.getTemperature());
        responseDto.setImage(product.getImage());
/*
        byte[] image = imageLoader.loadImageFromDisk(product.getImageUrl());
        String base64Image = Base64.getEncoder().encodeToString(image);
        responseDto.setBase64Image(base64Image);
*/
        return responseDto;
    }

}
