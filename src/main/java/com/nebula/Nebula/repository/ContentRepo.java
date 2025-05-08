package com.nebula.Nebula.repository;

import com.nebula.Nebula.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentRepo extends JpaRepository<Content, UUID> {
    Optional<Content> findByTopicsUrlSlug(String urlSlug);
}
