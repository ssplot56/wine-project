package com.project.wineshop.service;

import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.User;

public interface RegistrationService {
    User register(UserRegisterDto userRegisterDto);
}
