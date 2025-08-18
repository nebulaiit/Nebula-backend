package com.nebula.Nebula.language.repo;

import com.nebula.Nebula.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguageRepo extends JpaRepository<Language, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Language findBySlug(String slug);
}
