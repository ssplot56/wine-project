package com.project.wineproject.service.mapper;

public interface RequestDtoMapper<T, K> {
    T mapToModel(K k);
}
