package com.nebula.Nebula.language.overview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.language.entity.Language;
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
@Table(name = "company")
@Builder
public class CompanyName {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @JsonIgnore
    private Language language;
}
