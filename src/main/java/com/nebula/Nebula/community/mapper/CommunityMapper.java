package com.nebula.Nebula.community.mapper;

import com.nebula.Nebula.community.dtos.ReplyResponseDto;
import com.nebula.Nebula.community.dtos.ResponsePostDto;
import com.nebula.Nebula.community.model.Post;
import com.nebula.Nebula.community.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunityMapper {

    public ResponsePostDto toPostDto(Post post) {
        List<Reply> allReplies = post.getReplies();
        List<Reply> topLevelReplies = allReplies.stream()
                .filter(reply -> reply.getParent() == null)
                .toList();

        List<ReplyResponseDto> nestedReplies = topLevelReplies.stream()
                .map(this::mapReplyToDto)
                .collect(Collectors.toList());

        return ResponsePostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .tags(post.getTags())
                .replies(nestedReplies)
                .firstName(post.getUser().getFirstName())
                .lastName(post.getUser().getLastName())
                .reactions(post.getReactions())
                .build();
    }


    public ReplyResponseDto mapReplyToDto(Reply reply) {
        List<ReplyResponseDto> childDtos = reply.getChildren() != null ?
                reply.getChildren().stream()
                        .map(this::mapReplyToDto)
                        .collect(Collectors.toList())
                : new ArrayList<>();

        return ReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .author(reply.getAuthor())
                .createdAt(reply.getCreatedAt())
                .parent(reply.getParent() != null ? reply.getParent().getId() : null)
                .children(childDtos)
                .reactions(reply.getReactions())
                .build();
    }

}
