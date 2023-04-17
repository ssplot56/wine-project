package com.project.wineshop.service;

import com.project.wineshop.model.User;

public interface UserService extends GenericService<User> {
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);

    boolean mailIsAvailable(String email);

    boolean phoneNumberIsAvailable(String phoneNumber, String email);
}

