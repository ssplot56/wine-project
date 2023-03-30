package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.request.UserRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.mapper.impl.ProductMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto requestDto) {
        Product savedProduct = productService.save(productMapper.mapToModel(requestDto));
        return ResponseEntity.ok(productMapper.mapToDto(savedProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> responseDtos = productService.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getById(@PathVariable("productId") @Positive Long productId) {
        return ResponseEntity.ok(productService.getById(productId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") @Positive Long id,
                                                     @Valid @RequestBody ProductRequestDto requestDto) {
        Product updatedProduct = productService.update(id, productMapper.mapToModel(requestDto));
        ProductResponseDto updatedDto = productMapper.mapToDto(updatedProduct);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable("productId") @Positive Long productId) {
        productService.deleteById(productId);
    }

}
