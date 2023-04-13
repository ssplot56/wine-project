package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.user.UserRequestDto;
import com.project.wineshop.dto.request.user.UserUpdateRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<User, UserResponseDto> {
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(RoleService roleService,
                      ShippingDetailsService shippingDetailsService,
                      UserService userService,
                      PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shippingDetailsService = shippingDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
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

    public User mapToModel(UserUpdateRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setBirthDate(requestDto.getBirthDate());

        if (requestDto.getOldPassword() != null) {

            String oldPassword = userService.findByEmail(requestDto.getEmail()).getPassword();
            String oldPasswordFromRequest = requestDto.getOldPassword();
            if (passwordEncoder.matches(oldPasswordFromRequest, oldPassword)) {
                user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
            } else {
                throw new RuntimeException("Old password does not match the password in the database");
            }
        }

        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setCity(requestDto.getCity());
        shippingDetails.setRegion(requestDto.getRegion());
        shippingDetails.setWarehouse(requestDto.getWarehouse());
        shippingDetails.setDeliveryService(requestDto.getDeliveryService());
        user.setShippingDetails(shippingDetailsService
                .save(shippingDetails));

        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        return user;
    }
}
