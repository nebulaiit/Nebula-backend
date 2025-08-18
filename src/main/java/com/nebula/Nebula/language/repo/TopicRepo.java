package com.nebula.Nebula.language.repo;

import com.nebula.Nebula.language.entity.Language;
import com.nebula.Nebula.language.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRepo extends JpaRepository<Topic, UUID> {

    List<Topic> findByLanguage(Language language);

    boolean existsByNameIgnoreCase(String name);

    List<Topic> findByLanguageIdOrderByOrderIndexAsc(UUID languageId);
}
