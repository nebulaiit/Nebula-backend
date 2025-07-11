package com.nebula.Nebula.blogs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
@Builder
public class Blogs {

    @Id
    @GeneratedValue
    private UUID id;

    private String blogTitle;

    private String blogThumbnail;

    private String category;

    private String author;

    @Lob
    private String content;

    @Column(unique = true)
    private String slug;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
