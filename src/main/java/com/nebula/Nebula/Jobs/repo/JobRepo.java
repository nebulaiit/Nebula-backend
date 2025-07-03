package com.nebula.Nebula.Jobs.repo;


import com.nebula.Nebula.Jobs.model.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepo extends JpaRepository<JobVacancy, UUID> {


    JobVacancy findByJobTitleAndCompanyName(String jobTitle, String companyName);
}
