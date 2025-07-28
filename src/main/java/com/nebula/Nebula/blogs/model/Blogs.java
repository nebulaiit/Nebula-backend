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

    @Lob
    @Column(name = "blog_thumbnail", columnDefinition = "LONGBLOB")
    private byte[] blogThumbnail;

    private String category;

    private String author;

    @Lob
    private String content;

    @Column(unique = true)
    private String slug;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int views;       // increment on blog open/read
    private int likes;

}
