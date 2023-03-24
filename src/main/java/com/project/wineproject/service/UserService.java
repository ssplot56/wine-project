package com.project.wineproject.service;

import com.project.wineproject.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User update(Long id, User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}
