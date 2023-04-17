package com.project.wineshop.controller;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.exception.UserAlreadyExistException;
import com.project.wineshop.exception.UserWithSuchPhoneNumberExistException;
import com.project.wineshop.model.Order;
import com.project.wineshop.service.OrderService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderService orderService;
    private RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper;

    public OrderController(OrderService orderService,
                           RequestDtoMapper<Order, OrderRequestDto> requestDtoMapper) {
        this.orderService = orderService;
        this.requestDtoMapper = requestDtoMapper;
    }

    @PostMapping("/complete")
    public ResponseEntity completeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        orderService.completeOrder(requestDtoMapper.mapToModel(orderRequestDto));
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({UserAlreadyExistException.class, UserWithSuchPhoneNumberExistException.class})
    public ResponseEntity exceptionHandler(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}