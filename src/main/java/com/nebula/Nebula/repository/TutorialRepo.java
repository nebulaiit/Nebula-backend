package com.nebula.Nebula.repository;

import com.nebula.Nebula.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TutorialRepo extends JpaRepository<Tutorial, UUID> {
    Tutorial findByTutorialName(String tutorialName);
}
