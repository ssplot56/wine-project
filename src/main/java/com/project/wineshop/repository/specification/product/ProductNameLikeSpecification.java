package com.project.wineshop.repository.specification.product;

import com.project.wineshop.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductNameLikeSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "name";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] names) {
        return (root, query, criteriaBuilder) -> {
            Predicate[] predicates = new Predicate[names.length];
            for (int i = 0; i < names.length; i++) {
                predicates[i] = criteriaBuilder.like(root.get(FIELD_NAME), "%" + names[i] + "%");
            }
            return criteriaBuilder.and(predicates);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
