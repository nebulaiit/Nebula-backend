package com.nebula.Nebula.userdashboard.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.entity.Users;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.auth.repo.UserRepo;
import com.nebula.Nebula.userdashboard.model.WishlistItem;
import com.nebula.Nebula.userdashboard.repo.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private LearnerUserRepo userRepository;

    public List<WishlistItem> getWishList(UUID id) {
        return wishlistRepository.findByUserId(id);
    }

    public WishlistItem addToWishlist(UUID userId, WishlistItem item) {
        Optional<LearnerUser> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        LearnerUser user = userOptional.get();
        item.setUser(user);  // Assuming WishlistItem has setUser()
        return wishlistRepository.save(item);
    }
    public ResponseBodyDto removeFromWishlist(UUID itemId) {
        if (!wishlistRepository.existsById(itemId)) {
            throw new IllegalArgumentException("Wishlist item not found with ID: " + itemId);
        }

        wishlistRepository.deleteById(itemId);
     return ResponseBodyDto.builder().code(200).message("Wishlist removed successfully").build();
    }
}
