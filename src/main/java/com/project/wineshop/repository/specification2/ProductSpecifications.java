package com.project.wineshop.repository.specification2;

import com.project.wineshop.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasType(String type) {
        return (root, query, builder) -> builder.equal(root.get("type"), type);
    }

    public static Specification<Product> hasColor(String color) {
        return (root, query, builder) -> builder.equal(root.get("color"), color);
    }

    public static Specification<Product> hasEvent(String event) {
        return (root, query, builder) -> builder.equal(root.get("event"), event);
    }

    public static Specification<Product> hasDish(String dish) {
        return (root, query, builder) -> builder.like(root.get("dishes"), "%" + dish + "%");
    }
}
