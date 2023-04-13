package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShippingDetails;
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
        order.setIsGift(orderRequestDto.getIsGift());
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setRegion(orderRequestDto.getRegion());
        shippingDetails.setCity(orderRequestDto.getCity());
        shippingDetails.setDeliveryService(orderRequestDto.getDeliveryService());
        shippingDetails.setWarehouse(orderRequestDto.getWarehouse());
        return null;

    }
}
