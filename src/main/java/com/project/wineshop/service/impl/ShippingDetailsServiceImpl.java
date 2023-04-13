package com.project.wineshop.service.impl;

import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.repository.ShippingDetailsRepository;
import com.project.wineshop.service.ShippingDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingDetailsServiceImpl implements ShippingDetailsService {
    private final ShippingDetailsRepository shippingDetailsRepository;

    public ShippingDetailsServiceImpl(ShippingDetailsRepository shippingDetailsRepository) {
        this.shippingDetailsRepository = shippingDetailsRepository;
    }

    @Override
    public ShippingDetails save(ShippingDetails shippingDetails) {
        return shippingDetailsRepository.save(shippingDetails);
    }

    @Override
    public ShippingDetails findById(Long id) {
        return shippingDetailsRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find shippingDetails with id: " + id));
    }

    @Override
    public List<ShippingDetails> findAll() {
        return shippingDetailsRepository.findAll();
    }

    @Override
    public ShippingDetails update(Long id, ShippingDetails shippingDetails) {
        shippingDetails.setId(id);
        return shippingDetailsRepository.save(shippingDetails);
    }

    @Override
    public void deleteById(Long id) {
        shippingDetailsRepository.deleteById(id);
    }
}