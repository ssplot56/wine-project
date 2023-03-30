package com.project.wineshop.service;

import com.project.wineshop.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer save(Manufacturer manufacturer);

    Manufacturer getById(Long id);

    Manufacturer getByName(String name);

    List<Manufacturer> findAll();
}
