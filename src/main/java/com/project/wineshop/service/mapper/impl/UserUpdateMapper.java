package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.user.UserUpdateRequestDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateMapper implements RequestDtoMapper<User, UserUpdateRequestDto> {
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserUpdateMapper(RoleService roleService,
                            ShippingDetailsService shippingDetailsService,
                            UserService userService,
                            PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.shippingDetailsService = shippingDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User mapToModel(UserUpdateRequestDto requestDto) {
        if (requestDto.getPhoneNumber() != null
                && !userService.phoneNumberIsAvailable(requestDto.getPhoneNumber(),
                requestDto.getEmail())) {
            throw new RuntimeException("User with this phone number: "
                    + requestDto.getPhoneNumber()
                    + " already exists. Use another one.");
        }

        User userInDb = userService.findByEmail(requestDto.getEmail());

        User user = new User();
        user.setFirstName(requestDto.getFirstName() == null
                ? userInDb.getFirstName() : requestDto.getFirstName());
        user.setLastName(requestDto.getLastName() == null
                ? userInDb.getLastName() : requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNumber(requestDto.getPhoneNumber() == null
                ? userInDb.getPhoneNumber() : requestDto.getPhoneNumber());
        user.setBirthDate(requestDto.getBirthDate() == null
                ? userInDb.getBirthDate() : requestDto.getBirthDate());

        if (requestDto.getOldPassword() != null && isOldPasswordRight(requestDto)) {
            user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        }

        String region = requestDto.getRegion() == null
                ? userInDb.getShippingDetails().getRegion() : requestDto.getRegion();
        String city = requestDto.getCity() == null
                ? userInDb.getShippingDetails().getCity() : requestDto.getCity();
        String deliveryService = requestDto.getDeliveryService() == null
                ? userInDb.getShippingDetails().getDeliveryService()
                : requestDto.getDeliveryService();
        String warehouse = requestDto.getWarehouse() == null
                ? userInDb.getShippingDetails().getWarehouse() : requestDto.getWarehouse();

        ShippingDetails details = new ShippingDetails(region, city, deliveryService, warehouse);
        details.setId(userInDb.getShippingDetails().getId());
        user.setShippingDetails(shippingDetailsService.save(details));

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
