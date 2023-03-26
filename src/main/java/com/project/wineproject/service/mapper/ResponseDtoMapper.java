package com.project.wineproject.service.mapper;

public interface ResponseDtoMapper<K, T> {
    T mapToDto(K k);
}
