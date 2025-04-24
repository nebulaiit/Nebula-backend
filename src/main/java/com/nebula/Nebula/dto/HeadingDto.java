package com.nebula.Nebula.dto;


import com.nebula.Nebula.model.Topics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeadingDto {

    private UUID id;
    private String headingName;
    private List<TopicsDto> topics;
}
