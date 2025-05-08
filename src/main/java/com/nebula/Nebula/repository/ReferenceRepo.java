package com.nebula.Nebula.repository;

import com.nebula.Nebula.model.ReferenceContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReferenceRepo  extends JpaRepository<ReferenceContent,UUID>{

}