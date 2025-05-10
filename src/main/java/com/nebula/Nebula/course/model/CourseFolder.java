package com.nebula.Nebula.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "course_folder")
@Builder
public class CourseFolder {

    @Id
    @GeneratedValue
    private UUID id;

    private String folderName;

    private int orderIndex;

    @OneToMany(mappedBy = "courseFolder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UploadedContent> contents;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;
}
