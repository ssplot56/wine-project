package com.project.wineproject.service.impl;

import com.project.wineproject.model.ShoppingCart;
import com.project.wineproject.model.User;
import com.project.wineproject.repository.ShoppingCartRepository;
import com.project.wineproject.service.ShoppingCartService;
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
