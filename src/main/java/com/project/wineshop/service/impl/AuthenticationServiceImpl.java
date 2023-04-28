package com.project.wineshop.service.impl;

import com.project.wineshop.dto.request.user.UserLoginDto;
import com.project.wineshop.dto.request.user.UserRegisterDto;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.security.jwt.JwtTokenProvider;
import com.project.wineshop.service.AuthenticationService;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import java.util.Set;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final ShippingDetailsService shippingDetailsService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     ShippingDetailsService shippingDetailsService,
                                     UserService userService,
                                     RoleService roleService,
                                     PasswordEncoder passwordEncoder,
                                     JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.shippingDetailsService = shippingDetailsService;
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public User register(UserRegisterDto userRegisterDto) {
        if (!userService.mailIsAvailable(userRegisterDto.getEmail())) {
            throw new RuntimeException("Account with this email: "
                    + userRegisterDto.getEmail() + " already exists!");
        }

        ShippingDetails shippingDetails = new ShippingDetails("", "", "", "");

        User user = new User();
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setPhoneNumber(null);
        user.setBirthDate(null);
        user.setShippingDetails(shippingDetailsService.save(shippingDetails));
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        return userService.save(user);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(), userLoginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }
}
