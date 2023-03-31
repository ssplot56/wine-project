package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ShippingDetailsRequestDto;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper implements RequestDtoMapper
        <ShippingDetails, ShippingDetailsRequestDto> {
    @Override
    public ShippingDetails mapToModel(ShippingDetailsRequestDto requestDto) {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setRegion(requestDto.getRegion());
        shippingDetails.setCity(requestDto.getCity());
        shippingDetails.setDeliveryService(requestDto.getDeliveryService());
        shippingDetails.setWarehouse(requestDto.getWarehouse());
        return shippingDetails;
    }
}
