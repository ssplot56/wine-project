package com.project.wineshop.service;

import com.project.wineshop.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product getById(Long id);

    List<Product> findAll();

    Product update(Long id, Product product);

    void deleteById(Long id);
}
