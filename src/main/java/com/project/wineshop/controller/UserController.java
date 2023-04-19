package com.project.wineshop.controller;

import com.project.wineshop.dto.request.user.UserUpdateRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.model.User;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.impl.UserMapper;
import com.project.wineshop.service.mapper.impl.UserUpdateMapper;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserUpdateMapper userUpdateMapper;

    public UserController(UserService userService, UserMapper userMapper,
                          UserUpdateMapper userUpdateMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userUpdateMapper = userUpdateMapper;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                                  UserUpdateRequestDto requestDto) {
        User userWithoutId = userUpdateMapper.mapToModel(requestDto);
        User userWithId = userService.update(id, userWithoutId);
        return new ResponseEntity<>(userMapper.mapToDto(userWithId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userMapper.mapToDto(
                userService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return new ResponseEntity<>(userService.findAll().stream()
                .map(userMapper::mapToDto)
                .toList(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User with id: " + id + " was deleted.",
                HttpStatus.OK);
    }
}
