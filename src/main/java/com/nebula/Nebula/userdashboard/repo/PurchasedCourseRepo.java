package com.nebula.Nebula.userdashboard.repo;


import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.userdashboard.model.PurchasedCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchasedCourseRepo extends JpaRepository<PurchasedCourse, UUID> {
    List<PurchasedCourse> findByUser(LearnerUser user);
}
