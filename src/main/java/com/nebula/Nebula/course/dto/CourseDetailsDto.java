package com.nebula.Nebula.course.dto;

import com.nebula.Nebula.course.model.LearningPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDetailsDto {

    private UUID id;

    private String courseTitle;
    private String courseDescription;
    private String thumbnailUrl;
    private String category;

    // Ratings and stats
    private double averageRating;
    private int totalLearners;
    private int totalExercises;
    private double hoursOfContent;

    // What you'll learn
    private List<LearningPoint> whatYouWillLearn;

    // Skills (aka languages/technologies)
    private List<String> languages;

    // Optionally: testimonials
    private List<TestimonialDto> testimonials;
}
