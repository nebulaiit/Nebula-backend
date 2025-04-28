package com.nebula.Nebula.auth.repo;

import com.nebula.Nebula.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Users findByEmail(String username);

    @Query("SELECT u.userId FROM Users u ORDER BY u.userId DESC LIMIT 1")
    String findLastUserId();

    Users findByUserId(String userId);
}
