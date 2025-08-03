package com.nebula.Nebula.userdashboard.controller;

import com.nebula.Nebula.userdashboard.dto.CartItemDto;
import com.nebula.Nebula.userdashboard.model.CartItem;
import com.nebula.Nebula.userdashboard.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItemDto>> getUserCart(@PathVariable UUID userId) {

        List<CartItemDto> cartItemDtos = cartService.getUserCart(userId);

        return new ResponseEntity<>(cartItemDtos, HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addToCart(@RequestParam UUID userId, @RequestParam UUID courseId) {
//        cartService.addToCart(userId, courseId);
//        return ResponseEntity.ok("Course added to cart");
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<String> removeFromCart(@RequestParam UUID userId, @RequestParam UUID courseId) {
//        cartService.removeFromCart(userId, courseId);
//        return ResponseEntity.ok("Course removed from cart");
//    }


}
