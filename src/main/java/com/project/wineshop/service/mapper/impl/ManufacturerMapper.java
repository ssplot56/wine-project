package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.ManufacturerRequestDto;
import com.project.wineshop.dto.response.ManufacturerResponseDto;
import com.project.wineshop.model.Manufacturer;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper implements
        RequestDtoMapper<Manufacturer, ManufacturerRequestDto>,
        ResponseDtoMapper<Manufacturer, ManufacturerResponseDto> {
    @Override
    public Manufacturer mapToModel(ManufacturerRequestDto manufacturerRequestDto) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerRequestDto.getName());
        manufacturer.setCountry(manufacturerRequestDto.getCountry());
        return manufacturer;
    }

    @Override
    public ManufacturerResponseDto mapToDto(Manufacturer manufacturer) {
        ManufacturerResponseDto responseDto = new ManufacturerResponseDto();
        responseDto.setId(manufacturer.getId());
        responseDto.setName(manufacturer.getName());
        responseDto.setCountry(manufacturer.getCountry());
        return responseDto;
    }
}
