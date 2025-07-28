package com.nebula.Nebula.community.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.community.dtos.PostRequestDto;
import com.nebula.Nebula.community.dtos.ReplyRequestDto;
import com.nebula.Nebula.community.dtos.ResponsePostDto;
import com.nebula.Nebula.community.mapper.CommunityMapper;
import com.nebula.Nebula.community.model.Post;
import com.nebula.Nebula.community.model.Reply;
import com.nebula.Nebula.community.model.Tag;
import com.nebula.Nebula.community.repo.PostRepo;
import com.nebula.Nebula.community.repo.ReplyRepo;
import com.nebula.Nebula.community.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommunityService {


    @Autowired
    private LearnerUserRepo learnerUserRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private ReplyRepo replyRepo;

    @Autowired
    private CommunityMapper communityMapper;



    public ResponseBodyDto createPost(PostRequestDto post, UUID userId) {

        Post post1 = new Post();
        post1.setContent(post.getContent());

        LearnerUser learnerUser = learnerUserRepo.findById(userId).orElse(null);

        List<Tag> tagEntities = new ArrayList<>();

        for (String tagName : post.getTags()) {
            Tag tag = tagRepo.findByName(tagName.trim().toLowerCase())
                    .orElseGet(() -> {
                        Tag newTag = new Tag();
                        newTag.setName(tagName.trim().toLowerCase());
                        return tagRepo.save(newTag);
                    });
            tagEntities.add(tag);
        }

        post1.setTags(tagEntities);
        post1.setUser(learnerUser);

        postRepo.save(post1);

        return ResponseBodyDto.builder().code(201).message("Post created").build();
    }

    public List<ResponsePostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();

       return posts.stream().map(communityMapper::toPostDto).collect(Collectors.toList());
    }


    public ResponseBodyDto addReply(ReplyRequestDto dto) {
        Reply reply = new Reply();
        reply.setContent(dto.getContent());
        reply.setAuthor(dto.getAuthor());

        if (dto.getPostId() != null) {
            Post post = postRepo.findById(dto.getPostId()).orElseThrow();
            reply.setPost(post);
        }

        if (dto.getParentId() != null) {
            Reply parent = replyRepo.findById(dto.getParentId()).orElseThrow();
            reply.setParent(parent);
            reply.setPost(parent.getPost()); // Inherit post from parent reply
        }

        replyRepo.save(reply);

        return ResponseBodyDto.builder()
                .code(201)
                .message("Reply added successfully")
                .build();
    }

}
