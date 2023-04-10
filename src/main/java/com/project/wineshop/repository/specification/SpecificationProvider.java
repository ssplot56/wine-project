package com.project.wineshop.repository.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    //?color=red
    // &type=dry
    // &event=party+with+friends
    // &pair+with=cheese
    // &sort+by+popularity=high+to+low
    // &sort+by+price=low+to+high
    // &query=ww
    Specification<T> getSpecification(String[] params);

    String getFilterKey();
}
