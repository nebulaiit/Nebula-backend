package com.nebula.Nebula.language.dto;


import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseDto {
    private UUID id;
    private UUID topicId;
    private String topicName;
    private String title;
    private int orderIndex;
    private String contentHtml;
    private List<String> imageUrls;

}

