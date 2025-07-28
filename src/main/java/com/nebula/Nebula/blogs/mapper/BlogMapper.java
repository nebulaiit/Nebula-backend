package com.nebula.Nebula.blogs.mapper;
import java.util.Base64;

import com.nebula.Nebula.blogs.dto.BlogList;
import com.nebula.Nebula.blogs.model.Blogs;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BlogMapper {

    public BlogList toDto(Blogs blog) {
        String base64Image = blog.getBlogThumbnail() != null
                ? "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(blog.getBlogThumbnail())
                : null;

        return BlogList.builder()
                .id(blog.getId())
                .blogTitle(blog.getBlogTitle())
                .blogThumbnailBase64(base64Image)
                .category(blog.getCategory())
                .author(blog.getAuthor())
                .slug(blog.getSlug())
                .createdAt(blog.getCreatedAt())
                .build();
    }
}
