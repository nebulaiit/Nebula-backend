package com.nebula.Nebula.auth.repo;

import com.nebula.Nebula.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<Users, UUID> {
    Users findByEmail(String username);
}
