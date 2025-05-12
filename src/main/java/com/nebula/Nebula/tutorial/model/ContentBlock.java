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
@Builder
public class ContentBlock {

    @Id
    @GeneratedValue
    private UUID id;

    private String type; // heading, paragraph, image, code, video

    @Lob
    private String value;

    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "content_id")
    @JsonIgnore
    private Content content;
}
