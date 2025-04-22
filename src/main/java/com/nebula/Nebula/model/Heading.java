package com.nebula.Nebula.model;

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
@Table(name = "heading")
@Builder
public class Heading {

    @Id
    @GeneratedValue
    private UUID id;

    private String headingName;

    @OneToMany(mappedBy = "heading", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topics> topics;

}
