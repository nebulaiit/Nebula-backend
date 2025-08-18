package com.nebula.Nebula.Jobs.service;

import com.nebula.Nebula.Jobs.dto.JobDetailsDto;
import com.nebula.Nebula.Jobs.dto.JobVacancyDto;
import com.nebula.Nebula.Jobs.dto.JobVacancyRequestDto;
import com.nebula.Nebula.Jobs.mapper.JobVacancyMapper;
import com.nebula.Nebula.Jobs.model.Company;
import com.nebula.Nebula.Jobs.model.JobVacancy;
import com.nebula.Nebula.Jobs.repo.CompanyRepo;
import com.nebula.Nebula.Jobs.repo.JobRepo;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.entity.Users;
import com.nebula.Nebula.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {
    
    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private JobVacancyMapper jobVacancyMapper;
    
    public List<JobVacancyDto> getAllJobs() {
        List<JobVacancy> jobs = jobRepo.findAll();
        return jobs.stream()
                .map(JobVacancyMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseBodyDto createJobs(JobVacancyRequestDto job) {

        String companyName = job.getCompany().getName();

        UUID companyId = job.getCompany().getId();

        UUID userId =  job.getPostedBy().getId();

        JobVacancy existingJob = jobRepo.findByJobTitleAndCompanyName(job.getJobTitle(), companyName);

        if (existingJob != null) {
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Duplicate Entry: A job with this title already exists for the company.")
                    .build();
        }

        Company company = companyRepo.findById(companyId).orElse(null);


        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Map DTO to Entity
        JobVacancy jobToSave = JobVacancy.builder()
                .jobTitle(job.getJobTitle())
                .jobType(job.getJobType())
                .experience(job.getExperience())
                .salaryRange(job.getSalaryRange())
                .requiredSkills(job.getRequiredSkills())
                .education(job.getEducation())
                .jobDescription(job.getJobDescription())
                .responsibilities(job.getResponsibilities())
                .qualifications(job.getQualifications())
                .benefits(job.getBenefits())
                .isRemote(job.isRemote())
                .isActive(true)
                .postedDate(LocalDate.now())
                .lastDateToApply(job.getLastDateToApply())
                .company(company)
                .postedBy(user)
                .build();

        jobRepo.save(jobToSave);

        return ResponseBodyDto.builder()
                .code(201)
                .message("Job vacancy created successfully.")
                .build();
    }

    public JobDetailsDto getJobDetailsById(UUID id) {

        JobVacancy jobVacancy = jobRepo.findById(id).orElse(null);

        assert jobVacancy != null;
        return jobVacancyMapper.toDetailsDto(jobVacancy);
    }
}
