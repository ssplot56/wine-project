package com.project.wineproject.service.impl;

import com.project.wineproject.model.ShoppingCart;
import com.project.wineproject.model.User;
import com.project.wineproject.repository.ShoppingCartRepository;
import com.project.wineproject.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setProducts(new ArrayList<>());
        return cartRepository.save(cart);
    }
}
