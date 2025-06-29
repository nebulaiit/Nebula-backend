package com.nebula.Nebula.course.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.course.dto.CourseContentDto;
import com.nebula.Nebula.course.dto.CourseDto;
import com.nebula.Nebula.course.mapper.CourseMapper;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.course.model.CourseFolder;
import com.nebula.Nebula.course.model.UploadedContent;
import com.nebula.Nebula.course.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDto> getAllCourses() {

        List<Course> courseList= courseRepo.findAll();
        return courseList.stream().map(courseMapper::toCourseDto).collect(Collectors.toList());
    }

    public ResponseBodyDto createCourse(Course course) {

        Course course1 = courseRepo.findByCourseTitle(course.getCourseTitle());

        if (course1 == null){


            if (course.getCoursePrice() != null) {
                course.getCoursePrice().setCourse(course); // if bi-directional
            }

            if (course.getCourseFolders() != null) {
                int index = 1; // Start orderIndex from 1
                for (CourseFolder folder : course.getCourseFolders()) {
                    folder.setCourse(course);
                    folder.setOrderIndex(index++); // Assign orderIndex incrementally
                    if (folder.getContents() != null) {
                        for (UploadedContent content : folder.getContents()) {
                            content.setCourseFolder(folder);
                        }
                    }
                }
            }
            courseRepo.save(course);
            return ResponseBodyDto.builder().code(201).message("Course Has Been Added").build();

        }

        return ResponseBodyDto.builder().code(401).message("Course Already Present").build();
    }

    public Course getCourseById(UUID id) {

        return courseRepo.findById(id).orElse(null);
    }

    public ResponseBodyDto deleteCourseById(UUID id) {

        courseRepo.deleteById(id);

        return ResponseBodyDto.builder().code(200).message("Course Deleted Successfully").build();

    }

    public CourseContentDto getCourseContentByID(UUID id) {

        Course course = courseRepo.findById(id).orElse(null);

        return courseMapper.toDto(course);
    }
}
