package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.OrderPayment;
import com.project.wineshop.model.OrderStatus;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.User;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements RequestDtoMapper<Order, OrderRequestDto> {
    private final ProductService productService;
    private final UserMapper userMapper;

    public OrderMapper(ProductService productService, UserMapper userMapper) {
        this.productService = productService;
        this.userMapper = userMapper;
    }

    @Override
    public Order mapToModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setIsGift(orderRequestDto.getIsGift());
        order.setPayment(OrderPayment.Payment.valueOf(orderRequestDto.getPayment()));
        User user = userMapper.mapToModel(orderRequestDto.getUserRequest());
        order.setUser(user);
        order.setShippingDetails(user.getShippingDetails());
        order.setOrderStatus(OrderStatus.Status.CREATED);
        order.setOrderDate(LocalDateTime.now());
        Map<Product, Integer> products = orderRequestDto.getProducts()
                .entrySet().stream()
                .collect(Collectors.toMap(entry -> productService.getById(entry.getKey()),
                        Map.Entry::getValue));
        order.setProducts(products);
        return order;
    }
}
