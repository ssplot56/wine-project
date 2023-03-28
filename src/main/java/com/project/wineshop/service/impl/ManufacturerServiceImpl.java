package com.project.wineshop.service.impl;

import com.project.wineshop.model.Manufacturer;
import com.project.wineshop.repository.ManufacturerRepository;
import com.project.wineshop.service.ManufacturerService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find manufacturer with id: " + id));
    }

    @Override
    public Manufacturer getByName(String name) {
        return manufacturerRepository.findByName(name);
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer update(Long id, Manufacturer manufacturer) {
        manufacturer.setId(id);
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
