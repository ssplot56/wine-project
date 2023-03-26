package com.project.wineproject.service;

import com.project.wineproject.dto.request.UserRegisterDto;
import com.project.wineproject.model.User;

public interface RegistrationService {
    User register(UserRegisterDto userRegisterDto);
}
