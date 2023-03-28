package com.project.wineshop.service;

import com.project.wineshop.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User update(Long id, User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    User findByEmail(String email);
}
