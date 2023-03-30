package com.project.wineshop.controller;

import com.project.wineshop.dto.request.UserRequestDto;
import com.project.wineshop.dto.response.UserResponseDto;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.impl.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id,
                                                  UserRequestDto requestDto) {
        return new ResponseEntity<>(userMapper.mapToDto(
                userService.update(id, userMapper.mapToModel(requestDto))), HttpStatus.OK);
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
        return new ResponseEntity<>("User with id :" + id + " was deleted.",
                HttpStatus.OK);
    }
}
