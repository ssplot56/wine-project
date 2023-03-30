package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ProductRequestDto;
import com.project.wineshop.dto.response.ProductResponseDto;
import com.project.wineshop.model.Product;
import com.project.wineshop.service.ManufacturerService;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.mapper.impl.ProductMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto requestDto) {
        Product savedProduct = productService.save(productMapper.mapToModel(requestDto));
        return productMapper.mapToDto(savedProduct);
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.findAll()
                .stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable("productId") @Positive Long productId) {
        return productService.getById(productId);
    }

    //update()

    @DeleteMapping("/{productId}")
    public void deleteById(@PathVariable("productId") @Positive Long productId) {
        productService.deleteById(productId);
    }


/*
    @GetMapping("/inject")
    public void saveAndFindProducts() {
        Product product = new Product();
        product.setName("Chardonnay");
        product.setPrice(24.99);
        product.setColor(Product.Color.WHITE);
        product.setType(Product.Type.SEMISWEET);
        //Chardonnay from Carneros, California
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Carneros");
        manufacturer.setCountry("California_USA");
        Manufacturer manufacturerFromDB = manufacturerService.save(manufacturer);
        product.setManufacturer(manufacturerFromDB);
        product.setInStock(true);
        productService.save(product);
        productService.findAll().forEach(System.out::println);
    }
*/

}
