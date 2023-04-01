package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Manufacturer;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ProductColor;
import com.project.wineshop.model.ProductType;
import com.project.wineshop.service.ManufacturerService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements
        RequestDtoMapper<Product, ProductRequestDto>,
        ResponseDtoMapper<Product, ProductResponseDto> {
    private final ManufacturerService manufacturerService;

    public ProductMapper(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setColor(ProductColor.Color.valueOf(productRequestDto.getColor()));
        product.setType(ProductType.Type.valueOf(productRequestDto.getType()));
        product.setVintage(productRequestDto.getVintage());
        Manufacturer manufacturer = manufacturerService.getById(productRequestDto.getManufacturerId());
        product.setManufacturer(manufacturer);
        product.setPairing(productRequestDto.getPairing());
        product.setGrape(productRequestDto.getGrape());
        product.setTaste(productRequestDto.getTaste());
        product.setTemperature(productRequestDto.getTemperature());
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
        responseDto.setVintage(product.getVintage());
        responseDto.setManufacturer(product.getManufacturer());
        responseDto.setPairing(product.getPairing());
        responseDto.setGrape(product.getGrape());
        responseDto.setTaste(product.getTaste());
        responseDto.setTemperature(product.getTemperature());
        return responseDto;
    }
}
