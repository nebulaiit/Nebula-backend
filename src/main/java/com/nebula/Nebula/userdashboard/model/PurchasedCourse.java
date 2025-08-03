package com.nebula.Nebula.userdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.course.model.Course;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "purchased_courses")
public class PurchasedCourse {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private LearnerUser user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    private LocalDateTime purchaseDate;
}
