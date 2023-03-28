package com.project.wineshop.controller;

import com.project.wineshop.model.Manufacturer;
import com.project.wineshop.model.Product;
import com.project.wineshop.service.ManufacturerService;
import com.project.wineshop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService,
                             ManufacturerService manufacturerService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }

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
}
