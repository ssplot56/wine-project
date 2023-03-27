package com.project.wineproject.service.mapper.impl;

import com.project.wineproject.dto.request.UserRequestDto;
import com.project.wineproject.dto.response.UserResponseDto;
import com.project.wineproject.model.User;
import com.project.wineproject.service.mapper.RequestDtoMapper;
import com.project.wineproject.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<User, UserResponseDto> {
    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setBirthDate(requestDto.getBirthDate());
        user.setAddress(requestDto.getAddress());
        user.setRoles(requestDto.getRoles());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setPassword(user.getPassword());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        responseDto.setBirthDate(user.getBirthDate());
        responseDto.setAddress(user.getAddress());
        responseDto.setShoppingCart(user.getShoppingCart());
        responseDto.setRoles(user.getRoles());
        return responseDto;
    }
}
