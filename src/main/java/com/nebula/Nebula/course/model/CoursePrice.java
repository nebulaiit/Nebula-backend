package com.nebula.Nebula.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_price")
@Builder
public class CoursePrice {

    @Id
    @GeneratedValue
    private UUID id;

    private String validityType; // e.g., "Single Validity"

    private int duration;        // e.g., 1

    private String durationUnit; // e.g., "Year(s)"

    private double price;

    private double discount;

    private double effectivePrice;

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

}
