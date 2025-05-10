package com.nebula.Nebula.course.mapper;

import com.nebula.Nebula.course.dto.CourseContentDto;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.course.model.CourseFolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {


    public CourseContentDto toDto(Course course){
        if (course == null) {
            return null;
        }

        List<CourseFolder> courseFolders = course.getCourseFolders();

        return CourseContentDto.builder()
                .id(course.getId())
                .courseTitle(course.getCourseTitle())
                .courseFolders(courseFolders)
                .build();

    }
}
