package com.project.wineshop.service.mapper;

public interface ResponseDtoMapper<K, T> {
    T mapToDto(K k);
}
