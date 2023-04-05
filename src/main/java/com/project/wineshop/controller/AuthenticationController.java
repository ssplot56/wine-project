package com.project.wineshop.controller;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto);
        return new ResponseEntity<>("The user has been registered.", HttpStatus.CREATED);
    }
}
