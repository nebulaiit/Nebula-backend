package com.nebula.Nebula.course.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPoint {

    private String title;       // e.g., "JavaScript, React, & Node.js"
    private String description; // e.g., "Build fully-fledged websites and web apps."
}
