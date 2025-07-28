package com.nebula.Nebula.blogs.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.blogs.dto.BlogList;
import com.nebula.Nebula.blogs.dto.BlogRequest;
import com.nebula.Nebula.blogs.model.Blogs;
import com.nebula.Nebula.blogs.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/details/{slug}")
    public ResponseEntity<Blogs> getBlogBySlug(@PathVariable String slug){

        Blogs blogs = blogService.getBlogBySlug(slug);

        return new ResponseEntity<>(blogs, HttpStatus.OK);

    }

    @GetMapping("/blog-list")
    public ResponseEntity<List<BlogList>> getBlogList(){

        List<BlogList> blogLists = blogService.getBlogList();

        return new ResponseEntity<>(blogLists, HttpStatus.OK);
    }

    @PostMapping("/add-blog")
    public ResponseEntity<ResponseBodyDto> createBlog(
            @RequestPart("blog") BlogRequest blogs,
            @RequestPart("blogThumbnail") MultipartFile imageFile) throws IOException {

        ResponseBodyDto response = blogService.createBlog(blogs, imageFile);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
