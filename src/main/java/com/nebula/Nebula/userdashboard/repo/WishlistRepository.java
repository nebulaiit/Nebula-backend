package com.nebula.Nebula.userdashboard.repo;

import com.nebula.Nebula.userdashboard.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, UUID> {

    @Query("SELECT w FROM WishlistItem w WHERE w.user.id = :userId")
    List<WishlistItem> findByUserId(UUID userId);

    WishlistItem findByUserIdAndCourseId(UUID userId, UUID courseId);
}