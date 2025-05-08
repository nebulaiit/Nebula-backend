package com.nebula.Nebula.auth.repo;

import com.nebula.Nebula.auth.entity.LearnerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LearnerUserRepo extends JpaRepository<LearnerUser, UUID> {

    LearnerUser findByEmail(String username);
}
