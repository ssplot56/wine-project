package com.project.wineproject.controller;

import com.project.wineproject.dto.request.UserRegisterDto;
import com.project.wineproject.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> register(UserRegisterDto userRegisterDto) {
        registrationService.register(userRegisterDto);
        return new ResponseEntity<>("The user has been registered.", HttpStatus.CREATED);
    }
}
