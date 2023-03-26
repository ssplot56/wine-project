package com.project.wineproject.service;

import com.project.wineproject.model.ShoppingCart;
import com.project.wineproject.model.User;

public interface ShoppingCartService {
    ShoppingCart registerNewShoppingCart(User user);
}
