package com.project.wineshop.controller;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.UserRepository;
import com.project.wineshop.service.OrderService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper;
    private UserService userService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService,
                           RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper, UserService userService,
                           UserRepository userRepository) {
        this.orderService = orderService;
        this.requestDtoMapper = requestDtoMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/complete")
    public ResponseEntity completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        orderService.completeOrder(requestDtoMapper.mapToModel(orderRequestDto));
        return ResponseEntity.ok().build();
    }
}