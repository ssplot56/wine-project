//package com.project.wineshop.security;
//
//import com.project.wineshop.model.Role;
//import com.project.wineshop.model.User;
//import com.project.wineshop.service.UserService;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserService userService;
//
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        User user = userService.findByEmail(email);
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getPassword(),mapRoles(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> mapRoles(Set<Role> roles) {
//        return roles.stream()
//                .map(r -> new SimpleGrantedAuthority(r.getName().name()))
//                .collect(Collectors.toList());
//    }
//}
