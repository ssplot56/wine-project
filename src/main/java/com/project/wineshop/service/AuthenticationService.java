package com.project.wineshop.service;

import com.project.wineshop.dto.request.user.UserLoginDto;
import com.project.wineshop.dto.request.user.UserRegisterDto;
import com.project.wineshop.model.User;

public interface AuthenticationService {
    User register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);
}
