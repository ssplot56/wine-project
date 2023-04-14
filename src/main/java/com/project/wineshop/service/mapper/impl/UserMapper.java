package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.UserRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.exception.UserAlreadyExistException;
import com.project.wineshop.exception.UserWithSuchPhoneNumberExistException;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<User, UserResponseDto> {
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;

    private final ShippingDetailsMapper shippingDetailsMapper;
    private final UserService userService;

    public UserMapper(RoleService roleService,
                      ShippingDetailsService shippingDetailsService, ShippingDetailsMapper shippingDetailsMapper, UserService userService) {
        this.roleService = roleService;
        this.shippingDetailsService = shippingDetailsService;
        this.shippingDetailsMapper = shippingDetailsMapper;
        this.userService = userService;
    }

    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = userService.findByEmail(requestDto.getEmail());
        if(user == null) {
            user = new User();
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            user.setEmail(requestDto.getEmail());
            user.setPhoneNumber(requestDto.getPhoneNumber());
        } else if(user.getRoles().contains(Role.RoleName.USER) || (user.getRoles().contains(Role.RoleName.ADMIN))) {
            throw new UserAlreadyExistException("This email is already used");
        }
        if(userService.findByPhoneNumber(requestDto.getPhoneNumber()) != null) {
            throw new UserWithSuchPhoneNumberExistException("The user with such phone number already exist!");
        }
        ShippingDetails shippingDetails = shippingDetailsMapper.mapToModel(requestDto.getShippingDetailsRequest());

        user.setShippingDetails(shippingDetails);
//        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
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
