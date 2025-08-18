package com.nebula.Nebula.language.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicRequestDto {

    private String name;
    private UUID languageId;
}

