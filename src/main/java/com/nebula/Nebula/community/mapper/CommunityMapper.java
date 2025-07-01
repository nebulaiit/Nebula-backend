package com.nebula.Nebula.community.mapper;

import com.nebula.Nebula.community.dtos.ResponsePostDto;
import com.nebula.Nebula.community.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {

    public ResponsePostDto toPostDto(Post post) {
        return ResponsePostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .tags(post.getTags())
                .replies(post.getReplies())
                .reactions(post.getReactions())
                .build();
    }
}
