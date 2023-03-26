package com.project.wineproject.service.impl;

import com.project.wineproject.dto.request.UserRegisterDto;
import com.project.wineproject.model.Role;
import com.project.wineproject.model.User;
import com.project.wineproject.service.AddressService;
import com.project.wineproject.service.RegistrationService;
import com.project.wineproject.service.RoleService;
import com.project.wineproject.service.ShoppingCartService;
import com.project.wineproject.service.UserService;
import com.project.wineproject.service.mapper.impl.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final AddressService addressService;
    private final RoleService roleService;
    private final ShoppingCartService cartService;
    private final AddressMapper addressMapper;

    public RegistrationServiceImpl(UserService userService,
                                   AddressService addressService,
                                   RoleService roleService,
                                   ShoppingCartService cartService,
                                   AddressMapper addressMapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.roleService = roleService;
        this.cartService = cartService;
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
        cartService.registerNewShoppingCart(user);
        return userWithId;
    }
}
