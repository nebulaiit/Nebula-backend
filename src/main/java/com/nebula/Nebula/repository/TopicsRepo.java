package com.nebula.Nebula.repository;

import com.nebula.Nebula.model.Content;
import com.nebula.Nebula.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TopicsRepo extends JpaRepository<Topics, UUID> {

    Topics findByUrlSlug(String urlSlug);
}
