package com.project.wineshop.repository.specification.product;

import com.project.wineshop.model.Product;
import com.project.wineshop.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "type";
    private static final String FIELD_NAME = "type";

    @Override
    public Specification<Product> getSpecification(String[] types) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            for (String value : types) {
                predicate.value(value);
            }
            return criteriaBuilder.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
