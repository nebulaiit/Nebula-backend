package com.nebula.Nebula.course.repo;

import com.nebula.Nebula.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepo extends JpaRepository<Course, UUID> {
    Course findByCourseTitle(String courseTitle);
}
