package com.project.wineshop.service.mapper;

public interface RequestDtoMapper<T, K> {
    T mapToModel(K k);
}
