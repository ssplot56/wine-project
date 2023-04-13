package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ManufacturerRequestDto;
import com.project.wineshop.dto.response.ManufacturerResponseDto;
import com.project.wineshop.model.Manufacturer;
import com.project.wineshop.service.ManufacturerService;
import com.project.wineshop.service.mapper.impl.ManufacturerMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manufacturers")
@CrossOrigin(origins = "*")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ManufacturerMapper manufacturerMapper;

    public ManufacturerController(ManufacturerService manufacturerService, ManufacturerMapper manufacturerMapper) {
        this.manufacturerService = manufacturerService;
        this.manufacturerMapper = manufacturerMapper;
    }

    @PostMapping
    public ResponseEntity<ManufacturerResponseDto> create(
            @RequestBody @Valid ManufacturerRequestDto requestDto) {
        Manufacturer savedManufacturer = manufacturerService.save(manufacturerMapper.mapToModel(requestDto));
        return ResponseEntity.ok(manufacturerMapper.mapToDto(savedManufacturer));
    }

    @GetMapping
    public ResponseEntity<List<ManufacturerResponseDto>> getAll() {
        List<ManufacturerResponseDto> responseDtos = manufacturerService.findAll()
                .stream()
                .map(manufacturerMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getById(@PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok(manufacturerService.getById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDto> update(@PathVariable("id") @Positive Long id,
                                                          @RequestBody @Valid ManufacturerRequestDto requestDto) {
        Manufacturer updatedManufacturer = manufacturerService.update(id, manufacturerMapper.mapToModel(requestDto));
        ManufacturerResponseDto updatedDto = manufacturerMapper.mapToDto(updatedManufacturer);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") @Positive Long id) {
        manufacturerService.deleteById(id);
    }


}
