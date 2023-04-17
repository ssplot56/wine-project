package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.user.UserRequestDto;
import com.project.wineshop.dto.request.user.UserUpdateRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.exception.UserAlreadyExistException;
import com.project.wineshop.exception.UserWithSuchPhoneNumberExistException;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
        ResponseDtoMapper<User, UserResponseDto> {
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ShippingDetailsMapper shippingDetailsMapper;
    private final UserService userService;

    public UserMapper(RoleService roleService,
                      ShippingDetailsService shippingDetailsService,
                      UserService userService,
                      PasswordEncoder passwordEncoder) {
    private final PasswordEncoder encoder;

    public UserMapper(RoleService roleService, ShippingDetailsMapper shippingDetailsMapper, UserService userService, PasswordEncoder encoder) {
        this.roleService = roleService;
        this.shippingDetailsMapper = shippingDetailsMapper;
        this.userService = userService;
        this.encoder = encoder;
        this.shippingDetailsService = shippingDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapToModel(UserRequestDto requestDto) {
        Optional<User> userOptional = Optional.ofNullable(userService.findByEmail(requestDto.getEmail()));
//        boolean isUserOrAdmin = userOptional.isPresent() && isUserOrAdmin(userOptional.get());
        if (userOptional.isEmpty()) {
            userOptional = Optional.of(new User());
        } else if (requestDto.getPassword() != null && isUserOrAdmin(userOptional.get())) {
            throw new UserAlreadyExistException("This email is already used");
        }
        User user = createUserByData(userOptional, requestDto);
        ShippingDetails shippingDetails =
                shippingDetailsMapper.mapToModel(requestDto.getShippingDetailsRequest());
        user.setShippingDetails(shippingDetails);
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

    private boolean isUserOrAdmin(User user) {
       return  user.getRoles().contains(roleService.findByName(Role.RoleName.USER))
                || user.getRoles().contains(roleService.findByName(Role.RoleName.ADMIN));
    }

    private User createUserByData(Optional<User> userOptional,
                                  UserRequestDto requestDto) {
        User user = userOptional.get();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        Set<Role> roleSet = new HashSet<>();
        if (Optional.ofNullable(requestDto.getPassword()).isPresent()) {
            user.setPassword(encoder.encode(requestDto.getPassword()));
            roleSet.add(roleService.findByName(Role.RoleName.USER));
        } else {
            roleSet.add(roleService.findByName(Role.RoleName.GUEST));
        }
        user.setRoles(roleSet);
        if (userService.findByPhoneNumber(user.getPhoneNumber()) != null && isUserOrAdmin(user)) {
            throw new UserWithSuchPhoneNumberExistException("The user with such phone number" +
                    " already exist!");
        }

        if (Optional.ofNullable(requestDto.getBirthDate()).isPresent()) {
            user.setBirthDate(requestDto.getBirthDate());
        }
        return user;
    }

    public User mapToModel(UserUpdateRequestDto requestDto) {
        if (!userService.phoneNumberIsAvailable(requestDto.getPhoneNumber(),
                requestDto.getEmail())) {
            throw new RuntimeException("User with this phone number: "
                    + requestDto.getPhoneNumber()
                    + " already exists. Use another one.");
        }

        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        user.setBirthDate(requestDto.getBirthDate());

        if (requestDto.getOldPassword() != null && isOldPasswordRight(requestDto)) {
            user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        }

        ShippingDetails shippingDetails = new ShippingDetails(requestDto.getRegion(),
                requestDto.getCity(), requestDto.getWarehouse(),requestDto.getDeliveryService());
        shippingDetails.setId(userService.findByEmail(requestDto
                .getEmail()).getShippingDetails().getId());
        user.setShippingDetails(shippingDetailsService
                .save(shippingDetails));

        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        return user;
    }

    private boolean isOldPasswordRight(UserUpdateRequestDto requestDto) {
        String oldPassword = userService.findByEmail(requestDto.getEmail()).getPassword();
        String oldPasswordFromRequest = requestDto.getOldPassword();
        if (passwordEncoder.matches(oldPasswordFromRequest, oldPassword)) {
            return true;
        } else {
            throw new RuntimeException("Old password does not match the password in the database");
        }
    }
}
