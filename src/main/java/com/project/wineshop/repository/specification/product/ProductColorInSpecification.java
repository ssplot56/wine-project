package com.project.wineshop.repository.specification.product;

import com.project.wineshop.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductColorInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "color";
    private static final String FIELD_NAME = "color";

    @Override
    public Specification<Product> getSpecification(String[] colors) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            for (String value : colors) {
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
