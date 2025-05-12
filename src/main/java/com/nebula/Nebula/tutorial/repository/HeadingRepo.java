package com.nebula.Nebula.tutorial.repository;

import com.nebula.Nebula.tutorial.model.Heading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeadingRepo extends JpaRepository<Heading, UUID> {
}
