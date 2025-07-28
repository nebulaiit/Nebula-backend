package com.nebula.Nebula.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private UUID id;
    private String courseTitle;
    private String thumbnailUrl;
    private double price;
    private double discount;
    private double effectivePrice;
    private int duration;        // e.g., 1
    private String durationUnit; // e.g., "Year(s)"
    private String category;
}
