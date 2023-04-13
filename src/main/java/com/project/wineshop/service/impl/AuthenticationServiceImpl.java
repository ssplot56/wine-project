//package com.project.wineshop.service.impl;
//
//import com.project.wineshop.dto.request.UserLoginDto;
//import com.project.wineshop.dto.request.UserRegisterDto;
//import com.project.wineshop.model.Role;
//import com.project.wineshop.model.ShippingDetails;
//import com.project.wineshop.model.User;
//import com.project.wineshop.security.jwt.JwtTokenProvider;
//import com.project.wineshop.service.*;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService {
//    private final AuthenticationManager authenticationManager;
//    private final ShoppingCartService shoppingCartService;
//    private final ShippingDetailsService shippingDetailsService;
//    private final UserService userService;
//    private final RoleService roleService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider tokenProvider;
//
//    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
//                                     ShippingDetailsService shippingDetailsService, UserService userService,
//                                     RoleService roleService,
//                                     PasswordEncoder passwordEncoder,
//                                     ShoppingCartService shoppingCartService, JwtTokenProvider tokenProvider) {
//        this.authenticationManager = authenticationManager;
//        this.shippingDetailsService = shippingDetailsService;
//        this.userService = userService;
//        this.roleService = roleService;
//        this.passwordEncoder = passwordEncoder;
//        this.shoppingCartService = shoppingCartService;
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    public User register(UserRegisterDto userRegisterDto) {
//        ShippingDetails shippingDetails = new ShippingDetails();
//
//        User user = new User();
//        user.setFirstName(userRegisterDto.getFirstName());
//        user.setLastName(userRegisterDto.getLastName());
//        user.setEmail(userRegisterDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
//        user.setPhoneNumber(null);
//        user.setBirthDate(null);
//        user.setShippingDetails(shippingDetailsService.save(shippingDetails));
//        user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
//        User userWithId = userService.save(user);
//        shoppingCartService.registerNewShoppingCart(user);
//        return userWithId;
//    }
//
//    @Override
//    public String login(UserLoginDto userLoginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        userLoginDto.getEmail(), userLoginDto.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return tokenProvider.createToken(authentication);
//    }
//}
