package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.User;
import com.project.wineshop.service.*;
import com.project.wineshop.service.mapper.impl.ShippingDetailsMapper;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final RoleService roleService;
    private final ShippingDetailsService shippingDetailsService;
    private final ShoppingCartService shoppingCartService;
    private final ShippingDetailsMapper shippingDetailsMapper;

    public RegistrationServiceImpl(UserService userService,
                                   RoleService roleService,
                                   ShippingDetailsService shippingDetailsService,
                                   ShoppingCartService shoppingCartService,
                                   ShippingDetailsMapper shippingDetailsMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.shippingDetailsService = shippingDetailsService;
        this.shoppingCartService = shoppingCartService;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setBirthDate(userRegisterDto.getBirthDate());
        user.setShippingDetails(shippingDetailsService.save(shippingDetailsMapper
                .mapToModel(userRegisterDto.getAddress())));
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        User userWithId = userService.save(user);
        shoppingCartService.registerNewShoppingCart(user);
        return userWithId;
    }
}
