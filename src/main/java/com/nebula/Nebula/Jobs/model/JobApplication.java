package com.nebula.Nebula.Jobs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.LearnerUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {


    @Id
    @GeneratedValue
    private UUID id;

    private String applicantName;
    private String email;
    private String resumeUrl;
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobVacancy job;

    private LocalDate appliedDate;
}
