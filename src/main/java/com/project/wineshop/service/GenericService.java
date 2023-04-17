package com.project.wineshop.service;

import java.util.List;

public interface GenericService<K> {
    K save(K k);

    K findById(Long id);

    List<K> findAll();

    K update(Long id, K k);

    void deleteById(Long id);


}
