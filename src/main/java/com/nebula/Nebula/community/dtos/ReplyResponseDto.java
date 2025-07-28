package com.nebula.Nebula.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyResponseDto {
    private UUID id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private UUID parent; // ID of the parent reply
    private List<ReplyResponseDto> children = new ArrayList<>();
    private Map<String, Integer> reactions = new HashMap<>();
}
