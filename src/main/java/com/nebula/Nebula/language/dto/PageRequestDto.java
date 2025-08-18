package com.nebula.Nebula.language.dto;


import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDto {
    private UUID topicId;          // Which topic this page belongs to
    private String title;          // Page title
    private String contentHtml;    // Full HTML content
    private List<String> imageUrls; // Optional: multiple image URLs
}

