package com.nebula.Nebula.course.dto;

import com.nebula.Nebula.course.model.CourseFolder;
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
public class CourseContentDto {

    private UUID id;
    private String courseTitle;
    private List<CourseFolder> courseFolders;
}
