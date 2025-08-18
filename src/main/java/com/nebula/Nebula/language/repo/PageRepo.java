package com.nebula.Nebula.language.repo;

import com.nebula.Nebula.language.entity.Page;
import com.nebula.Nebula.language.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PageRepo extends JpaRepository<Page, UUID> {
    List<Page> findByTopicSlug(String slug);

    List<Page> findByTopic(Topic topic);
}
