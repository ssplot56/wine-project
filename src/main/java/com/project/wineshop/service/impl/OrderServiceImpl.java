package com.project.wineshop.service.impl;

import com.project.wineshop.model.Order;
import com.project.wineshop.model.enums.OrderStatus;
import com.project.wineshop.repository.OrderRepository;
import com.project.wineshop.service.OrderService;
import com.project.wineshop.service.ShippingDetailsService;
import com.project.wineshop.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {
    private final ShippingDetailsService shippingDetailsService;
    private final UserService userService;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(ShippingDetailsService shippingDetailsService, UserService userService, OrderRepository orderRepository) {
        this.shippingDetailsService = shippingDetailsService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order completeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.Status.CREATED);
        if(order.getUser().getPassword() != null && userService.findByEmail(order.getUser().getEmail()) == null) {
            shippingDetailsService.save(order.getUser().getShippingDetails());
            userService.save(order.getUser());
        }
        orderRepository.save(order);
        return order;
    }
}
