package com.nebula.Nebula.userdashboard.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.userdashboard.model.WishlistItem;
import com.nebula.Nebula.userdashboard.service.WishlistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // ✅ Get all wishlist items for a user
    @GetMapping("/{id}")
    public ResponseEntity<List<WishlistItem>> getWishlist(@PathVariable UUID id) {
        List<WishlistItem> wishlistItem = wishlistService.getWishList(id);
        return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
    }

    // ✅ Add a course to the user's wishlist
    @PostMapping("/{id}")
    public ResponseEntity<WishlistItem> addToWishlist(@PathVariable UUID id, @RequestBody WishlistItem item) {
        WishlistItem savedItem = wishlistService.addToWishlist(id, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // ✅ Remove a course from the user's wishlist
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> removeFromWishlist(@PathVariable UUID id) {
        ResponseBodyDto responseBodyDto =  wishlistService.removeFromWishlist(id);
        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
