package com.project.wineshop.service.impl;

import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.ShoppingCartRepository;
import com.project.wineshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart addProduct(User user, Product product) {
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getProducts().add(product);
        cartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return cartRepository.findShoppingCartByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);
    }

    @Override
    public ShoppingCart clear(ShoppingCart shoppingCart) {
        shoppingCart.setProducts(new ArrayList<>());
        cartRepository.save(shoppingCart);
        return shoppingCart;
    }
}
