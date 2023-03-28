package com.project.wineshop.service.impl;

import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.ShoppingCartRepository;
import com.project.wineshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart registerNewShoppingCart(User user) {
        ShoppingCart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new RuntimeException("Shopping cart not found!"));
        cart.setUser(user);
        return cartRepository.save(cart);
    }
}
