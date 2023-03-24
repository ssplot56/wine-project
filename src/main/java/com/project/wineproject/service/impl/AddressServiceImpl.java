package com.project.wineproject.service.impl;

import com.project.wineproject.model.Address;
import com.project.wineproject.repository.AddressRepository;
import com.project.wineproject.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find address with id: " + id));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
