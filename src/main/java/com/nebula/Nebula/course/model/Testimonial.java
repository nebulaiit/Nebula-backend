package com.nebula.Nebula.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nebula.Nebula.auth.entity.LearnerUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "testimonials")
public class Testimonial {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 1000)
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learner_user_id")
    @JsonIgnore
    private LearnerUser learnerUser;
}
