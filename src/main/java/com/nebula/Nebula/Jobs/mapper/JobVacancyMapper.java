package com.nebula.Nebula.Jobs.mapper;

import com.nebula.Nebula.Jobs.dto.JobDetailsDto;
import com.nebula.Nebula.Jobs.dto.JobVacancyDto;
import com.nebula.Nebula.Jobs.model.JobVacancy;
import org.springframework.stereotype.Component;

@Component
public class JobVacancyMapper {


    public static  JobVacancyDto toDto(JobVacancy job) {

        return JobVacancyDto.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .companyName(job.getCompanyName())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .experience(job.getExperience())
                .salaryRange(job.getSalaryRange())
                .postedDate(job.getPostedDate())
                .build();

    }

    public JobDetailsDto toDetailsDto(JobVacancy job) {
        return JobDetailsDto.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .companyName(job.getCompanyName())
                .companyLogoUrl(job.getCompanyLogoUrl())
                .location(job.getLocation())
                .jobType(job.getJobType())
                .experience(job.getExperience())
                .salaryRange(job.getSalaryRange())
                .requiredSkills(job.getRequiredSkills())
                .education(job.getEducation())
                .jobDescription(job.getJobDescription())
                .responsibilities(job.getResponsibilities())
                .qualifications(job.getQualifications())
                .benefits(job.getBenefits())
                .contactEmail(job.getContactEmail())
                .contactPhone(job.getContactPhone())
                .applyUrl(job.getApplyUrl())
                .isRemote(job.isRemote())
                .isActive(job.isActive())
                .postedDate(job.getPostedDate())
                .lastDateToApply(job.getLastDateToApply())
                .build();
    }
}
