package com.nebula.Nebula.blogs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogList {

    private UUID id;
    private String blogTitle;
    private String blogThumbnail;
    private String category;
    private String author;
    private String slug;
    private LocalDateTime createdAt;
}
