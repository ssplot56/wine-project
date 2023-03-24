package com.project.wineproject.service;

import com.project.wineproject.model.Address;
import java.util.List;

public interface AddressService {
    Address save(Address address);

    Address findById(Long id);

    List<Address> findAll();

    void delete(Long id);
}
