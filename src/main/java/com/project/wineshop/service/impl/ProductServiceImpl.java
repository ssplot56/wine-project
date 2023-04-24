package com.project.wineshop.service.impl;

import com.project.wineshop.model.Product;
import com.project.wineshop.repository.ProductRepository;
import com.project.wineshop.repository.specification.SpecificationManager;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.utility.PageRequestFormer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SpecificationManager<Product> productSpecificationManager;

    public ProductServiceImpl(ProductRepository productRepository,
                              SpecificationManager<Product> productSpecificationManager) {
        this.productRepository = productRepository;
        this.productSpecificationManager = productSpecificationManager;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setPopularity(product.getPopularity() + 1L);
        productRepository.save(product);
        return product;
    }

    @Override
    public List<Product> findAll(Map<String, String> params) {
        Integer page = 0;
        if (params.containsKey("page")) {
            page = Integer.valueOf(params.get("page"));
            params.remove("page");
        }
        Integer size = 6;
        if (params.containsKey("size")) {
            size = Integer.valueOf(params.get("size"));
            params.remove("size");
        }
        String sortBy = "id:ASC";
        if (params.containsKey("sortBy")) {
            sortBy = params.get("sortBy");
            params.remove("sortBy");
        }
        PageRequest pageRequest = PageRequestFormer.formPageRequest(page, size, sortBy);

        Specification<Product> specification = null;
        if (!params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                        entry.getValue().split(","));
                specification = specification == null ? Specification.where(sp) : specification.and(sp);
            }
        }
        if (specification == null) {
            return productRepository.findAll(pageRequest).toList();
        } else {
            return productRepository.findAll(specification, pageRequest).toList();
        }
    }

    @Override
    public Product update(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
