package com.project.wineshop.repository.specification;

import com.project.wineshop.model.Product;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.project.wineshop.repository.specification.product.SpecificationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecificationManager implements SpecificationManager<Product> {
    private final Map<String, SpecificationProvider<Product>> providersMap;

    @Autowired
    public ProductSpecificationManager(List<SpecificationProvider<Product>> productSpecifications) {
        this.providersMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Product> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
