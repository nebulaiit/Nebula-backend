package com.nebula.Nebula.userdashboard.repo;

import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.userdashboard.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByUserAndPurchasedFalse(LearnerUser user);
    boolean existsByUserAndCourseAndPurchasedFalse(LearnerUser user, Course course);
    void deleteByUserAndCourse(LearnerUser user, Course course);
}