package com.nebula.Nebula.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutorial")
@Builder
public class Tutorial {

    @Id
    @GeneratedValue
    private UUID id;

    private String tutorialName;

    @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Heading> heading;

    @OneToOne(mappedBy = "tutorial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReferenceContent referenceContent;


}
