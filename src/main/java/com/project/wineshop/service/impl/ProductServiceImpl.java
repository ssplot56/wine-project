package com.project.wineshop.service.impl;

import com.project.wineshop.model.Product;
import com.project.wineshop.repository.ProductRepository;
import com.project.wineshop.repository.specification.SpecificationManager;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.utility.PageRequestFormer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
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
        Map<String, String> paramsProcessed = joinAllSortBy(params);
        Integer page = 0;
        if (paramsProcessed.containsKey("page")) {
            page = Integer.valueOf(paramsProcessed.get("page"));
            paramsProcessed.remove("page");
        }
        Integer size = 6;
        if (paramsProcessed.containsKey("size")) {
            size = Integer.valueOf(paramsProcessed.get("size"));
            paramsProcessed.remove("size");
        }
        String sortBy = "id:ASC";
        if (paramsProcessed.containsKey("sortBy")) {
            sortBy = paramsProcessed.get("sortBy");
            paramsProcessed.remove("sortBy");
        }
        PageRequest pageRequest = PageRequestFormer.formPageRequest(page, size, sortBy);

        Specification<Product> specification = null;
        if (!paramsProcessed.isEmpty()) {
            for (Map.Entry<String, String> entry : paramsProcessed.entrySet()) {
                Specification<Product> sp = productSpecificationManager.get(entry.getKey(),
                        entry.getValue().split(","));
                specification = specification == null ? Specification.where(sp)
                        : specification.and(sp);
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

    //костиль спеціально на прохання фронтенду, щоб він міг відправляти запити так як йому зручно
    //це звичайно bad practice, але доводиться якось знаходити спільну мову з фронтендом
    private Map<String, String> joinAllSortBy(Map<String, String> params) {
        Map<String, String> result = new HashMap<>();
        Map<String, String> sortParams = new HashMap<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().contains("sortBy")) {
                sortParams.put(entry.getKey(), entry.getValue());
            } else {
                result.put(entry.getKey(), entry.getValue());
            }
        }

        if (!sortParams.isEmpty()) {
            String sortBy = sortParams.entrySet().stream()
                    .map(e -> e.getKey().replace("sortBy", "").toLowerCase() + ":" + e.getValue())
                    .collect(Collectors.joining(";"));
            result.put("sortBy", sortBy);
        }

        return result;
    }
}
