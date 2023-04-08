package com.project.wineshop.controller;

import com.project.wineshop.dto.response.ShoppingCartResponseDto;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping_carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponseDto> addProductToCart(HttpSession session,
                                                                    Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            String sessionId = session.getId();
        }
        return null;
    }
}
