package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.dto.request.OrderRequestNewUserDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderForNewUserMapper implements RequestDtoMapper<Order, OrderRequestNewUserDto> {
    private ProductService productService;

    public OrderForNewUserMapper(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Order mapToModel(OrderRequestNewUserDto orderRequestNewUserDto) {
        Order order = new Order();
        order.setIsGift(orderRequestNewUserDto.getIsGift());
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setRegion(orderRequestNewUserDto.getRegion());
        shippingDetails.setCity(orderRequestNewUserDto.getCity());
        shippingDetails.setDeliveryService(orderRequestNewUserDto.getDeliveryService());
        shippingDetails.setWarehouse(orderRequestNewUserDto.getWareHouse());
        User user = new User();
        user.setFirstName(orderRequestNewUserDto.getFirstName());
        user.setLastName(orderRequestNewUserDto.getLastName());
        user.setPhoneNumber(orderRequestNewUserDto.getPhoneNumber());
        user.setEmail(orderRequestNewUserDto.getEmail());
        user.setShippingDetails(shippingDetails);
        user.setPassword(orderRequestNewUserDto.getPassword());
        order.setUser(user);
        Map<Product, Integer> products = new HashMap<>();

        for (Map.Entry<Long, Integer> entry: orderRequestNewUserDto.getProducts().entrySet()) {
            products.put(productService.getById(entry.getKey()), entry.getValue());
        }
        order.setProducts(products);
        return order;
    }
}
