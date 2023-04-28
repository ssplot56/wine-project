package com.project.wineshop.service.impl;

import com.project.wineshop.exception.DataNotFoundException;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.UserRepository;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.UserService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Can't find user with id: " + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean mailIsAvailable(String email) {
        User user = findByEmail(email);
        return user == null
                || user.getRoles().contains(roleService.findByName(Role.RoleName.GUEST));
    }

    @Override
    public boolean phoneNumberIsAvailable(String phoneNumber, String email) {
        User user = findByEmail(email);
        String userPhoneNumber = (user == null) ? null : user.getPhoneNumber();
        if (userPhoneNumber != null && userPhoneNumber.equals(phoneNumber)) {
            return true;
        }
        List<String> existPhoneNumbers = userRepository.findAll().stream()
                .map(User::getPhoneNumber)
                .filter(Objects::nonNull)
                .toList();
        return !existPhoneNumbers.contains(phoneNumber);
    }
}
