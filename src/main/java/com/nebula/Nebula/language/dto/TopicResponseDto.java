package com.nebula.Nebula.language.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponseDto {
    private UUID id;
    private String name;
    private String slug;
    private int orderIndex;
}
