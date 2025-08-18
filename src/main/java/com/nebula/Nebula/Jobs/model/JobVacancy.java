package com.nebula.Nebula.Jobs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobVacancy {

    @Id
    @GeneratedValue
    private UUID id;

    private String jobTitle;

    private String jobType; // Full-time, Part-time, Internship, Contract, Remote

    private String experience; // "Fresher", "2-5 years", etc.

    private String salaryRange; // "₹4 LPA - ₹8 LPA"

    @ElementCollection
    @CollectionTable(name = "job_required_skills", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    private List<String> requiredSkills;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @ManyToOne
    @JoinColumn(name = "posted_by_user_id")
    @JsonIgnore
    private Users postedBy;

    private String education; // "B.Tech, B.E, MCA"

    @Column(length = 5000)
    private String jobDescription;

    @ElementCollection
    @CollectionTable(name = "job_responsibilities", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "responsibility")
    private List<String> responsibilities;

    @ElementCollection
    @CollectionTable(name = "job_qualifications", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "qualification")
    private List<String> qualifications;

    @ElementCollection
    @CollectionTable(name = "job_benefits", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "benefit")
    private List<String> benefits;

    private boolean isRemote;

    private boolean isActive;

    private LocalDate postedDate;

    private LocalDate lastDateToApply;
}
