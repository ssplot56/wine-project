package com.project.wineshop.controller;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        registrationService.register(userRegisterDto);
        return new ResponseEntity<>("The user has been registered.", HttpStatus.CREATED);
    }
}
