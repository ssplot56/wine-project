package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.User;
import com.project.wineshop.service.RegistrationService;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShoppingCartService;
import com.project.wineshop.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    public RegistrationServiceImpl(UserService userService,
                                   RoleService roleService,
                                   ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        User userWithId = userService.save(user);
        shoppingCartService.registerNewShoppingCart(user);
        return userWithId;
    }
}
