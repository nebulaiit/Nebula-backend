package com.nebula.Nebula.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateContentRequest {
    private String contentHeading;
    private List<ContentBlockDTO> blocks;
}
