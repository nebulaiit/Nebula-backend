package com.nebula.Nebula.auth.entity;

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
@Builder
@Table(name = "learner_users")
public class LearnerUser {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String verificationCode;

    private String headline;

    private String bio;

    private String phoneNumber;

    private boolean enabled = false;

    @Column(nullable = false)
    private String authProvider;  // "GOOGLE" or "LOCAL"


}
