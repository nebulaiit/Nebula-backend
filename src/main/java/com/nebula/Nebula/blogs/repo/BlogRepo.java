package com.nebula.Nebula.blogs.repo;

import com.nebula.Nebula.blogs.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogRepo extends JpaRepository<Blogs , UUID> {
    Blogs findBySlug(String slug);

    Blogs findByBlogTitle(String blogTitle);

    boolean existsBySlug(String slug);
}
