package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.AddressRequestDto;
import com.project.wineshop.model.Address;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements RequestDtoMapper<Address, AddressRequestDto> {
    @Override
    public Address mapToModel(AddressRequestDto addressRequestDto) {
        Address address = new Address();
        address.setCity(addressRequestDto.getCity());
        address.setStreet(addressRequestDto.getStreet());
        address.setHouseNumber(addressRequestDto.getHouseNumber());
        return address;
    }
}
