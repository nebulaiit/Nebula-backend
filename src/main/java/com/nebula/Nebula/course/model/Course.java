package com.nebula.Nebula.course.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
@Builder
public class Course {

    @Id
    @GeneratedValue
    private UUID id;

    private String courseTitle;

    private String courseDescription;

    // Binary thumbnail image (e.g., JPEG, PNG)
    private String thumbnailUrl;


    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CoursePrice coursePrice;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseFolder> courseFolders;
}
