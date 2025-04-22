package com.nebula.Nebula.auth.repo;

import com.nebula.Nebula.auth.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorityRepo extends JpaRepository<Authority, UUID> {
    Authority findByRoleCode(String employee);
}
