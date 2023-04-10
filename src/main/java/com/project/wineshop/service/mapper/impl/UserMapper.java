package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.UserRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<User, UserResponseDto> {
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;

    public UserMapper(RoleService roleService,
                      ShippingDetailsService shippingDetailsService) {
        this.roleService = roleService;
        this.shippingDetailsService = shippingDetailsService;
    }

    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setBirthDate(requestDto.getBirthDate());

        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setRegion(requestDto.getRegion());
        shippingDetails.setCity(requestDto.getCity());
        shippingDetails.setWarehouse(requestDto.getWarehouse());
        shippingDetails.setDeliveryService(requestDto.getDeliveryService());

        user.setShippingDetails(shippingDetailsService
                .save(shippingDetails));
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setBirthDate(user.getBirthDate());
        responseDto.setShippingDetails(user.getShippingDetails());
        return responseDto;
    }
}
