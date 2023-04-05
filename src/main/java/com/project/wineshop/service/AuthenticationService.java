package com.project.wineshop.service;

import com.project.wineshop.dto.request.UserLoginDto;
import com.project.wineshop.dto.request.UserRegisterDto;
import com.project.wineshop.model.User;

public interface AuthenticationService {
    User register(UserRegisterDto userRegisterDto);

    User login(UserLoginDto userLoginDto);
}
