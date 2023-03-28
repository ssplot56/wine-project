package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.User;
import com.project.wineshop.service.*;
import com.project.wineshop.service.mapper.impl.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final RoleService roleService;
    private final AddressService addressService;
    private final ShoppingCartService shoppingCartService;
    private final AddressMapper addressMapper;

    public RegistrationServiceImpl(UserService userService,
                                   RoleService roleService,
                                   AddressService addressService,
                                   ShoppingCartService shoppingCartService,
                                   AddressMapper addressMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
        this.shoppingCartService = shoppingCartService;
        this.addressMapper = addressMapper;
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
        user.setAddress(addressService.save(addressMapper
                .mapToModel(userRegisterDto.getAddress())));
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        User userWithId = userService.save(user);
        shoppingCartService.registerNewShoppingCart(user);
        return userWithId;
    }
}
