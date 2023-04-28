package com.project.wineshop.repository.specification.product;

import com.project.wineshop.model.Dish;
import com.project.wineshop.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.SetJoin;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductDishInSpecification implements SpecificationProvider<Product> {
    private static final String FILTER_KEY = "dish";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Product> getSpecification(String[] dishes) {
        return (root, query, criteriaBuilder) -> {
            SetJoin<Product, Dish> products = root.joinSet("dishes", JoinType.LEFT);
            CriteriaBuilder.In<String> predicate = criteriaBuilder.in(products.get(FIELD_NAME));
            for (String value : dishes) {
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
