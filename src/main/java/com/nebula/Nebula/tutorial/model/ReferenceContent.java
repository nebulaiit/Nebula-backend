package com.nebula.Nebula.tutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reference_content")
@Builder
public class ReferenceContent {
    @Id
    @GeneratedValue
    private UUID id;

    private String referenceTitle;

    @Lob
    private String referencePara;

    @OneToOne
    @JoinColumn(name = "tutorial_id")
    @JsonIgnore
    private Tutorial tutorial;

}