package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.enums.OrderPayment;
import com.project.wineshop.model.enums.OrderStatus;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderMapper implements RequestDtoMapper<Order, OrderRequestDto> {
    private UserService userService;
    private ProductService productService;

    public OrderMapper(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Order mapToModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
//        OrderPayment.Payment.valueOf("CREDIT_CARD");
        OrderPayment.Payment.valueOf(orderRequestDto.getPayment());
//        if(orderRequestDto.getPayment().equals("Credit card")) {
//            order.setPayment(OrderPayment.Payment.CREDIT_CARD);
//        } else {
//            order.setPayment(OrderPayment.Payment.GOOGLE_PAY);
//        }
        order.setIsGift(orderRequestDto.getIsGift());
        order.setUser(userService.findById(orderRequestDto.getUserId()));
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry: orderRequestDto.getProducts().entrySet()) {

            products.put(productService.getById(entry.getKey()), entry.getValue());
        }
        order.setProducts(products);
        return order;
    }
}
