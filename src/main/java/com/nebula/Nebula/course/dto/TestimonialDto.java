package com.nebula.Nebula.course.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestimonialDto {

    private UUID id;

    private String feedback;

    // Learner info
    private String learnerFullName;
    private String learnerHeadline;
    private String learnerAvatarUrl;  // You can map this from a profile/avatar field if available
}
