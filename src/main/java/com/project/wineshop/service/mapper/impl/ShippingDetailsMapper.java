package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ShippingDetailsRequestDto;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper implements RequestDtoMapper
        <ShippingDetails, ShippingDetailsRequestDto> {
    private final ShippingDetailsService shippingDetailsService;

    public ShippingDetailsMapper(ShippingDetailsService shippingDetailsService) {
        this.shippingDetailsService = shippingDetailsService;
    }

    @Override
    public ShippingDetails mapToModel(ShippingDetailsRequestDto requestDto) {
        ShippingDetails shippingDetails =
                shippingDetailsService.findShippingDetailsByAllFields(requestDto.getRegion(),
                        requestDto.getCity(),
                        requestDto.getDeliveryService(),
                        requestDto.getWarehouse());
        if (shippingDetails == null) {
            shippingDetails = new ShippingDetails();
            shippingDetails.setRegion(requestDto.getRegion());
            shippingDetails.setCity(requestDto.getCity());
            shippingDetails.setDeliveryService(requestDto.getDeliveryService());
            shippingDetails.setWarehouse(requestDto.getWarehouse());
        }
        return shippingDetails;
    }
}
