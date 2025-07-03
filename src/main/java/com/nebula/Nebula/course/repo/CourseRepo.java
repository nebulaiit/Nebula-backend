package com.nebula.Nebula.course.repo;

import com.nebula.Nebula.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepo extends JpaRepository<Course, UUID> {
    Course findByCourseTitle(String courseTitle);

    @Query("SELECT c FROM Course c JOIN c.languages l WHERE LOWER(l) = LOWER(:language)")
    List<Course> findByLanguage(@Param("language") String language);
}
