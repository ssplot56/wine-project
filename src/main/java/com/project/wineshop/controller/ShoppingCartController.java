package com.project.wineshop.controller;

import com.project.wineshop.dto.request.ShoppingCartRequestDto;
import com.project.wineshop.dto.response.ShoppingCartResponseDto;
import com.project.wineshop.model.ShoppingCart;
import com.project.wineshop.model.User;
import com.project.wineshop.service.ProductService;
import com.project.wineshop.service.ShoppingCartService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ResponseDtoMapper<ShoppingCart, ShoppingCartResponseDto> responseDtoMapper;

    private final RequestDtoMapper<ShoppingCart, ShoppingCartRequestDto> requestDtoMapper;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  ResponseDtoMapper<ShoppingCart, ShoppingCartResponseDto> responseDtoMapper, RequestDtoMapper<ShoppingCart, ShoppingCartRequestDto> requestDtoMapper, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
        this.productService = productService;

    }

    @GetMapping("/{id}")
    public ShoppingCartResponseDto getById(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(shoppingCartService.getById(id));
    }

    @PostMapping("/add-product")
    public ResponseEntity addToCart(@RequestParam Long productId,
                                    @RequestParam Integer quantity) {
        System.out.println(productId + " " + quantity);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("remove-product")
//    public ResponseEntity deleteFromCart(Authentication authentication,
//                                         @RequestParam Long productId,
//                                         @RequestParam Integer quantity) {
//        UserDetails details = (UserDetails) authentication.getPrincipal();
//        String username = details.getUsername();
//        User user = userService.findByEmail(username);
//        shoppingCartService.
//    }



//    @GetMapping("/by-user")
//    public ShoppingCartResponseDto getShoppingCartByUser(Authentication authentication) {
//        UserDetails details = (UserDetails) authentication.getPrincipal();
//        String username = details.getUsername();
//        User user = userService.findByEmail(username);
//        return responseDtoMapper.mapToDto(shoppingCartService.getByUser(user));
////        return ResponseEntity.ok(shoppingCartService.getByUser(user));
//    }

//    @PostMapping("/add-product")
//    public ResponseEntity addToCart(Authentication authentication,
//                                    @RequestParam Long productId,
//                                    @RequestParam Integer quantity) {
//        UserDetails details = (UserDetails) authentication.getPrincipal();
//        String email = details.getUsername();
//        User user = userService.findByEmail(email);
//        shoppingCartService.addProduct(user,productId,quantity);
//        return ResponseEntity.ok().build();
//    }

//    @PutMapping("update-product/{id}")
//    public ShoppingCartResponseDto updateCart(Authentication authentication,
//                                              @PathVariable Long id,
//                                              @RequestParam Integer quantity) {
//        UserDetails details = (UserDetails) authentication.getPrincipal();
//        String email = details.getUsername();
//        User user = userService.findByEmail(email);
//        productService.
//    }
}
