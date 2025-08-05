package com.nebula.Nebula.Jobs.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String website;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "company")
    private List<JobVacancy> jobs;
}
