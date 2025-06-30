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
    public ResponseEntity<ResponseBodyDto> createBlog(@RequestBody BlogRequest blogs){

        ResponseBodyDto responseBodyDto = blogService.createBlog(blogs);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }
}
