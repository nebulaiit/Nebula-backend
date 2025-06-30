package com.nebula.Nebula.blogs.mapper;

import com.nebula.Nebula.blogs.dto.BlogList;
import com.nebula.Nebula.blogs.model.Blogs;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public BlogList toDto(Blogs blogs) {

        return BlogList.builder()
                .id(blogs.getId())
                .blogTitle(blogs.getBlogTitle())
                .blogThumbnail(blogs.getBlogThumbnail())
                .category(blogs.getCategory())
                .author(blogs.getAuthor())
                .slug(blogs.getSlug())
                .createdAt(blogs.getCreatedAt())
                .build();
    }
}
