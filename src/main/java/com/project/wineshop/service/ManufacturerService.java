package com.project.wineshop.service;

import com.project.wineshop.model.Manufacturer;

public interface ManufacturerService extends GenericService<Manufacturer> {
    Manufacturer getByName(String name);
}
