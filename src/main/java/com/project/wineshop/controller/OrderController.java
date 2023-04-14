package com.project.wineshop.controller;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.User;
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

    public OrderController(OrderService orderService,
                           RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper, UserService userService) {
        this.orderService = orderService;
        this.requestDtoMapper = requestDtoMapper;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public ResponseEntity completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto, Authentication authentication) {
//        if(authentication.getPrincipal())

        orderService.completeOrder(requestDtoMapper.mapToModel(orderRequestDto));
        return ResponseEntity.ok().build();
    }
}