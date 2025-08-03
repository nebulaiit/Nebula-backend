package com.nebula.Nebula.userdashboard.service;

import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.course.repo.CourseRepo;
import com.nebula.Nebula.userdashboard.dto.CartItemDto;
import com.nebula.Nebula.userdashboard.mapper.CartItemMapper;
import com.nebula.Nebula.userdashboard.model.CartItem;
import com.nebula.Nebula.userdashboard.repo.CartItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private LearnerUserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;


    public List<CartItemDto> getUserCart(UUID userId) {
        LearnerUser user = userRepo.findById(userId).orElseThrow();
        List<CartItem> cartItems =  cartItemRepo.findByUserAndPurchasedFalse(user);

        return cartItems.stream()
                .map(CartItemMapper::toDto)
                .toList();

    }
}
