package com.project.wineshop.service;

import com.project.wineshop.model.User;

public interface UserService extends GenericService<User> {
    User findByEmail(String email);
}