package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.UserLoginDto;
import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.User;
import com.project.wineshop.service.AuthenticationService;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShoppingCartService;
import com.project.wineshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        User userWithId = userService.save(user);
        shoppingCartService.registerNewShoppingCart(user);
        return userWithId;
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        User user = userService.findByEmail(userLoginDto.getEmail());
        String encodedPassword = passwordEncoder.encode(userLoginDto.getPassword());
        if (!user.getPassword().equals(encodedPassword)) {
            throw new RuntimeException("Incorrect email or password!");
        }
        return user;
    }
}
