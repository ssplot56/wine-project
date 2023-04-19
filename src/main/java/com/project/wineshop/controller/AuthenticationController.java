package com.project.wineshop.controller;

import com.project.wineshop.dto.request.user.UserLoginDto;
import com.project.wineshop.dto.request.user.UserRegisterDto;
import com.project.wineshop.dto.response.JwtAuthResponse;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.User;
import com.project.wineshop.service.AuthenticationService;
import com.project.wineshop.service.mapper.impl.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterDto userRegisterDto) {
        User user = authenticationService.register(userRegisterDto);
        return new ResponseEntity<>(userMapper.mapToDto(user), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        String token = authenticationService.login(userLoginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
