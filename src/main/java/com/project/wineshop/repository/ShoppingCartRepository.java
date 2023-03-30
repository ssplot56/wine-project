package com.project.wineshop.repository;

import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    void deleteShoppingCartByUserId(Long userid);
    void add(ShoppingCart shoppingCart);
    ShoppingCart getByUser(User user);
    ShoppingCart update(ShoppingCart shoppingCart);
}
