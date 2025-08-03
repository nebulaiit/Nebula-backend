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

    @Column(nullable = false)
    private String category;  // ✅ New category field

    @ElementCollection
    @CollectionTable(name = "course_languages", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "language")
    private List<String> languages;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CoursePrice coursePrice;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseFolder> courseFolders;

    @ElementCollection
    @CollectionTable(name = "course_learning_points", joinColumns = @JoinColumn(name = "course_id"))
    private List<LearningPoint> whatYouWillLearn;
}
