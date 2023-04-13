package com.project.wineshop.repository.specification.product;

import com.project.wineshop.model.Product;
import com.project.wineshop.repository.specification.SpecificationProvider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductEventInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "event";
    private static final String FIELD_NAME = "event";
    @Override
    public Specification<Product> getSpecification(String[] events) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(root.get(FIELD_NAME));
            for (String value : events) {
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
