package com.nebula.Nebula.course.mapper;

import com.nebula.Nebula.course.dto.CourseContentDto;
import com.nebula.Nebula.course.dto.CourseDetailsDto;
import com.nebula.Nebula.course.dto.CourseDto;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.course.model.CourseFolder;
import com.nebula.Nebula.course.model.LearningPoint;
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
                .thumbnailUrl(course.getThumbnailUrl())
                .price(course.getCoursePrice().getPrice())
                .discount(course.getCoursePrice().getDiscount())
                .effectivePrice(course.getCoursePrice().getEffectivePrice())
                .duration(course.getCoursePrice().getDuration())
                .durationUnit(course.getCoursePrice().getDurationUnit())
                .category(course.getCategory())
                .build();
    }

    public CourseDetailsDto mapToCourseDetailsDto(Course course) {
        return CourseDetailsDto.builder()
                .id(course.getId())
                .courseTitle(course.getCourseTitle())
                .courseDescription(course.getCourseDescription())
                .thumbnailUrl(course.getThumbnailUrl())
                .category(course.getCategory())

                // You may calculate these dynamically in future
                .averageRating(4.7)
                .totalLearners(15000)
                .totalExercises(126)
                .hoursOfContent(87.6)

                .languages(course.getLanguages())

                .whatYouWillLearn(
                        course.getWhatYouWillLearn() != null
                                ? course.getWhatYouWillLearn().stream()
                                .map(lp -> new LearningPoint(lp.getTitle(), lp.getDescription()))
                                .toList()
                                : List.of()
                )

                .build();
    }

}
