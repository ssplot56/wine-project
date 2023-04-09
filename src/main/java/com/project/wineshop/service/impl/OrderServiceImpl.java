package com.project.wineshop.service.impl;

import com.project.wineshop.model.Order;
import com.project.wineshop.model.User;
import com.project.wineshop.model.enums.OrderPayment;
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
        User byEmail = userService.findByEmail(order.getUser().getEmail());
        if(byEmail == null) {
            shippingDetailsService.save(order.getUser().getShippingDetails());
            userService.save(order.getUser());
        }
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.Status.CREATED);
        orderRepository.save(order);
        return order;
    }
}
