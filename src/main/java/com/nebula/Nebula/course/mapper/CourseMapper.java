package com.nebula.Nebula.course.mapper;

import com.nebula.Nebula.course.dto.CourseContentDto;
import com.nebula.Nebula.course.dto.CourseDto;
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

    public CourseDto toCourseDto(Course course) {
        if (course == null) {
            return null;
        }


        return CourseDto.builder()
                .id(course.getId())
                .courseTitle(course.getCourseTitle())
                .price(course.getCoursePrice().getPrice())
                .discount(course.getCoursePrice().getDiscount())
                .duration(course.getCoursePrice().getDuration())
                .durationUnit(course.getCoursePrice().getDurationUnit())
                .build();
    }
}
