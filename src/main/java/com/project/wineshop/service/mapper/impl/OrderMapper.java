package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.OrderRequestDto;
import com.project.wineshop.model.Order;
import com.project.wineshop.model.Product;
import com.project.wineshop.model.Role;
import com.project.wineshop.model.ShippingDetails;
import com.project.wineshop.model.User;
import com.project.wineshop.model.enums.OrderPayment;
import com.project.wineshop.model.enums.OrderStatus;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.RoleService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class OrderMapper implements RequestDtoMapper<Order, OrderRequestDto> {
    private UserService userService;
    private ProductService productService;
    private RoleService roleService;

    public OrderMapper(UserService userService, ProductService productService, RoleService roleService) {
        this.userService = userService;
        this.productService = productService;
        this.roleService = roleService;
    }

    @Override
    public Order mapToModel(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setIsGift(orderRequestDto.getIsGift());
        order.setPayment(OrderPayment.Payment.valueOf(orderRequestDto.getPayment()));
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setRegion(orderRequestDto.getRegion());
        shippingDetails.setCity(orderRequestDto.getCity());
        shippingDetails.setDeliveryService(orderRequestDto.getDeliveryService());
        shippingDetails.setWarehouse(orderRequestDto.getWarehouse());
        User user = userService.findByEmail(orderRequestDto.getEmail());
        if(user == null) {
            user = new User();
        }
        user.setEmail(orderRequestDto.getEmail());
        user.setFirstName(orderRequestDto.getFirstName());
        user.setLastName(orderRequestDto.getLastName());
        user.setShippingDetails(shippingDetails);
        user.setPhoneNumber(orderRequestDto.getPhoneNumber());
        user.setRoles(Set.of(roleService.findByName(Role.RoleName.GUEST)));
        if(orderRequestDto.getCreateAccount() != null && orderRequestDto.getCreateAccount()) {
            user.setPassword(orderRequestDto.getPassword());
            user.setRoles(Set.of(roleService.findByName(Role.RoleName.USER)));
        }
        order.setUser(user);
        order.setOrderStatus(OrderStatus.Status.CREATED);
        order.setOrderDate(LocalDateTime.now());
        Map<Product, Integer> products = new HashMap<>();
        for(Map.Entry<Long, Integer> entry: orderRequestDto.getProducts().entrySet()) {
            products.put(productService.getById(entry.getKey()),entry.getValue());
        }
        order.setProducts(products);
        return order;
    }
}
