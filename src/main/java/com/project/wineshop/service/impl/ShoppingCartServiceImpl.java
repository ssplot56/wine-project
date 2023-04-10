package com.project.wineshop.service.impl;

import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.repository.ShoppingCartRepository;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    @Override
    public ShoppingCart addProduct(User user, Long productId, Integer quantity) {
        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getProducts().put(productService.getById(productId),quantity);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartRepository.findShoppingCartByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setProducts(new HashMap<>());
        shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCart clear(ShoppingCart shoppingCart) {
        shoppingCart.setProducts(new HashMap<>());
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow();
    }
}
