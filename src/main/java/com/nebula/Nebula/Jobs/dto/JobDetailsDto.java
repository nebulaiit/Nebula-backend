package com.nebula.Nebula.Jobs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDetailsDto {

    private UUID id;

    private String jobTitle;

    private String companyName;

    private String companyLogoUrl;

    private String location;

    private String jobType;

    private String experience;

    private String salaryRange;

    private List<String> requiredSkills;

    private String education;

    private String jobDescription;

    private String responsibilities;

    private String qualifications;

    private String benefits;

    private String contactEmail;

    private String contactPhone;

    private String applyUrl;

    private boolean isRemote;

    private boolean isActive;

    private LocalDate postedDate;

    private LocalDate lastDateToApply;
}