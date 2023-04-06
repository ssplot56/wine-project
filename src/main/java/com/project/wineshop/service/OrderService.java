package com.project.wineshop.service;

import com.project.wineshop.model.Order;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;

import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
