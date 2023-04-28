package com.project.wineshop.service;

import com.project.wineshop.model.Order;

public interface OrderService {
    Order completeOrder(Order order);
}
