package com.nebula.Nebula.language.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageDto {

    private UUID id;
    private String name;
    private String slug;
    private String category;
}
