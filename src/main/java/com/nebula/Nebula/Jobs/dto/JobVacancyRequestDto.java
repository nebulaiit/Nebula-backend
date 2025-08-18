package com.nebula.Nebula.Jobs.dto;

import com.nebula.Nebula.auth.dto.UserDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobVacancyRequestDto {

    private UUID id;
    private String jobTitle;
    private String jobType;
    private String experience;
    private String salaryRange;
    private List<String> requiredSkills;
    private String education;
    private String jobDescription;
    private List<String> responsibilities;
    private List<String> qualifications;
    private List<String> benefits;
    private boolean isRemote;
    private boolean isActive;
    private LocalDate postedDate;
    private LocalDate lastDateToApply;
    private CompanyDto company;
    private UserDto postedBy;
}

