package com.nebula.Nebula.blogs.service;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.blogs.dto.BlogList;
import com.nebula.Nebula.blogs.dto.BlogRequest;
import com.nebula.Nebula.blogs.mapper.BlogMapper;
import com.nebula.Nebula.blogs.model.Blogs;
import com.nebula.Nebula.blogs.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogMapper blogMapper;

    public Blogs getBlogBySlug(String slug) {

        return blogRepo.findBySlug(slug);
    }

    public List<BlogList> getBlogList() {

        List<Blogs> blogs = blogRepo.findAll();

        return blogs.stream().map(blogMapper::toDto).collect(Collectors.toList());
    }

    public ResponseBodyDto createBlog(BlogRequest blogs, MultipartFile imagesFile) throws IOException {

        Blogs existingBlog = blogRepo.findByBlogTitle(blogs.getBlogTitle());

        if (existingBlog != null){

            return ResponseBodyDto.builder().code(400).message("Blog already exist with "+ blogs.getBlogTitle()).build();

        }
        else {

            System.out.println(blogs);
            String slug = generateSlug(blogs.getBlogTitle());

            // Optional: check if slug already exists (handle duplicates)
            int count = 0;
            String originalSlug = slug;
            while (blogRepo.existsBySlug(slug)) {
                count++;
                slug = originalSlug + "-" + count;
            }

            Blogs blogs1 = Blogs.builder()
                    .blogTitle(blogs.getBlogTitle())
                    .blogThumbnail(imagesFile.getBytes())
                    .author(blogs.getAuthor())
                    .category(blogs.getCategory())
                    .content(blogs.getContent())
                    .slug(slug)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();


            blogRepo.save(blogs1);

            return ResponseBodyDto.builder().code(201).message("Blog Added Successfully").build();

        }
    }
    public String generateSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")     // remove special characters
                .replaceAll("\\s+", "-")           // replace spaces with hyphens
                .replaceAll("-+", "-")             // remove duplicate hyphens
                .replaceAll("^-|-$", "");          // trim leading/trailing hyphens
    }
}
