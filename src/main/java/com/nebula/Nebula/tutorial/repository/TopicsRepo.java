package com.nebula.Nebula.tutorial.repository;

import com.nebula.Nebula.tutorial.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicsRepo extends JpaRepository<Topics, UUID> {

    Topics findByUrlSlug(String urlSlug);
}
