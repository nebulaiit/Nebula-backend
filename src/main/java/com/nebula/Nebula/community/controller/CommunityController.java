package com.nebula.Nebula.community.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.community.dtos.PostRequestDto;
import com.nebula.Nebula.community.dtos.ResponsePostDto;
import com.nebula.Nebula.community.model.Post;
import com.nebula.Nebula.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @PostMapping("/post")
    public ResponseEntity<ResponseBodyDto> createPost(@RequestBody PostRequestDto post) {

        ResponseBodyDto responseBodyDto = communityService.createPost(post);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<ResponsePostDto>> getAllPosts(){

        List<ResponsePostDto> posts = communityService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
