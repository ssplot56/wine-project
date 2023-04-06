package com.project.wineshop.service.impl;

import com.project.wineshop.model.Order;
import com.project.wineshop.model.OrderStatus;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.OrderRepository;
import com.project.wineshop.service.OrderService;
import com.project.wineshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.Status.CREATED);
//        order.setProducts();
        order.setUser(shoppingCart.getUser());
//        order.setPayment();
//        order.setOrderStatus();
        orderRepository.save(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
//        return orderRepository.getOrdersHistory(user);
        return null;
    }
}
