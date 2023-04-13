package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Dish;
import com.project.wineshop.model.Product;
import com.project.wineshop.service.DishService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        product.setType(productRequestDto.getType());
        product.setColor(productRequestDto.getColor());
        product.setEvent(productRequestDto.getEvent());
        product.setPairing(productRequestDto.getPairing());
        List<Dish> dishes = new ArrayList<>();
        List<String> names = productRequestDto.getDishes();
        for (String name : names) {
            Dish dish = dishService.getByName(name);
            if (dish == null) {
                dish = new Dish();
                dish.setName(name);
                dishService.save(dish);
            }
            dishes.add(dish);
        }
        product.setDishes(new HashSet<>(dishes));
        product.setVintage(productRequestDto.getVintage());
        product.setCountry(productRequestDto.getCountry());
        product.setRegion(productRequestDto.getRegion());
        product.setGrape(productRequestDto.getGrape());
        product.setTaste(productRequestDto.getTaste());
        product.setTemperature(productRequestDto.getTemperature());
        product.setImageLink(productRequestDto.getImageLink());
        return product;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setPrice(product.getPrice());
        responseDto.setColor(product.getColor());
        responseDto.setType(product.getType());
        responseDto.setEvent(product.getEvent());
        responseDto.setPairing(product.getPairing());
        Set<Dish> dishes = product.getDishes();
        Set<String> dishNames = new HashSet<>();
        for (Dish dish : dishes) {
            dishNames.add(dish.getName());
        }
        responseDto.setDishes(dishNames);
        responseDto.setVintage(product.getVintage());
        responseDto.setCountry(product.getCountry());
        responseDto.setRegion(product.getRegion());
        responseDto.setGrape(product.getGrape());
        responseDto.setTaste(product.getTaste());
        responseDto.setTemperature(product.getTemperature());
        responseDto.setImageLink(product.getImageLink());
        return responseDto;
    }

}
