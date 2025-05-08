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
@Table(name = "topics")
@Builder
public class Topics {

    @Id
    @GeneratedValue
    private UUID id;

    private String topicName;

    @Column(unique = true, nullable = false)
    private String urlSlug; // for routing like /java/variables

    @ManyToOne
    @JoinColumn(name = "heading_id")
    @JsonIgnore
    private Heading heading;

    @OneToMany(mappedBy = "topics" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Content> contents;



}
