package com.nebula.Nebula.Jobs.dto;


import lombok.*;

import java.time.LocalDate;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobVacancyDto {

    private UUID id;
    private String jobTitle;
    private String companyName;
    private String location;
    private String jobType; // Full-time, Remote etc.
    private String experience;
    private String salaryRange;
    private LocalDate postedDate;

}

