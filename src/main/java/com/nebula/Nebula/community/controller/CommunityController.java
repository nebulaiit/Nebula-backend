package com.nebula.Nebula.community.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.community.dtos.PostRequestDto;
import com.nebula.Nebula.community.dtos.ReplyRequestDto;
import com.nebula.Nebula.community.dtos.ResponsePostDto;
import com.nebula.Nebula.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/post/{id}")
    public ResponseEntity<ResponseBodyDto> createPost(@RequestBody PostRequestDto post, @PathVariable UUID id) {

        ResponseBodyDto responseBodyDto = communityService.createPost(post,id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<ResponsePostDto>> getAllPosts(){

        List<ResponsePostDto> posts = communityService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/add-reply")
    public ResponseEntity<ResponseBodyDto> addReply(@RequestBody ReplyRequestDto replyDto) {
        ResponseBodyDto response = communityService.addReply(replyDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
