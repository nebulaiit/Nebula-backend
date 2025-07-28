package com.nebula.Nebula.community.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDto {
    private UUID postId;     // Used when replying to a post
    private UUID parentId;   // Used when replying to another reply
    private String content;
    private String author;   // Optional: could be derived from JWT in future
}
