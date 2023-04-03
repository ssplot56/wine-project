package com.project.wineshop.controller;

import com.project.wineshop.dto.response.ShoppingCartResponseDto;

import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.service.ShoppingCartService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/shopping_carts")
public class ShoppingCartController {
}
