package com.project.wineshop.service;

import com.project.wineshop.model.Address;
import java.util.List;

public interface AddressService {
    Address save(Address address);

    Address findById(Long id);

    List<Address> findAll();

    void delete(Long id);
}
