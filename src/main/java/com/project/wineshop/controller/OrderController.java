package com.project.wineshop.controller;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.dto.request.OrderRequestNewUserDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.service.OrderService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper;

    private RequestDtoMapper<Order, OrderRequestNewUserDto> requestNewUserDtoMapper;

    public OrderController(OrderService orderService, RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper, RequestDtoMapper<Order, OrderRequestNewUserDto> requestNewUserDtoMapper) {
        this.orderService = orderService;
        this.requestDtoMapper = requestDtoMapper;
        this.requestNewUserDtoMapper = requestNewUserDtoMapper;
    }

    @PostMapping("/complete")
    public ResponseEntity completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.completeOrder(requestDtoMapper.mapToModel(orderRequestDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/complete-new-user")
    public ResponseEntity completeOrderWithNewUser(@RequestBody OrderRequestNewUserDto orderRequestNewUserDto) {
        orderService.completeOrder(requestNewUserDtoMapper.mapToModel(orderRequestNewUserDto));
        return ResponseEntity.ok().build();
    }
}
