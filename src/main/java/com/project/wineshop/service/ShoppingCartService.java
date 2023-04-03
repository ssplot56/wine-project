package com.project.wineshop.service;

import com.project.wineshop.model.Product;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;

public interface ShoppingCartService {
    ShoppingCart addProduct(User user, Product product, Integer value);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    ShoppingCart clear(ShoppingCart shoppingCart);

}
