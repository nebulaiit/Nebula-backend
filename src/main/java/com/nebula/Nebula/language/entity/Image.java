package com.nebula.Nebula.language.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Builder
public class Image {
    @Id
    @GeneratedValue
    private UUID id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "page_id")
    @JsonIgnore
    private Page page;
}

