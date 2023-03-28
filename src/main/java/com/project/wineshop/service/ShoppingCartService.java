package com.project.wineshop.service;

import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;

public interface ShoppingCartService {
    ShoppingCart registerNewShoppingCart(User user);
}
