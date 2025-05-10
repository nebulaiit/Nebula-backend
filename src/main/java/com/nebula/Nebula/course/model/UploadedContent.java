package com.nebula.Nebula.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "uploaded_content")
@Builder
public class UploadedContent {

    @Id
    @GeneratedValue
    private UUID id;

    private String fileName;

    private String fileType; // "Text", "Document", "Image", "Video", "Folder", "Zip", etc.

    private String label; // UI label for categorization

    private LocalDateTime uploadedAt = LocalDateTime.now();

    // Binary data (only for Text, Document, etc.)
    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;

    // URL for Images or Videos
    private String fileUrl;


    @ManyToOne
    @JoinColumn(name = "course_folder_id")
    @JsonIgnore
    private CourseFolder courseFolder;
}
