package com.project.wineshop.repository.specification.product;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {

    Specification<T> getSpecification(String[] params);

    String getFilterKey();
}
