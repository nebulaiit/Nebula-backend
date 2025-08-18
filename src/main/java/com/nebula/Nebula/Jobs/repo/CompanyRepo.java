package com.nebula.Nebula.Jobs.repo;

import com.nebula.Nebula.Jobs.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface CompanyRepo extends JpaRepository<Company , UUID> {
   Company findByName(String name);
}
