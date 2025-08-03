package com.nebula.Nebula.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdate {

    private String firstName;
    private String lastName;
    private String email;
    private String headline;
    private String bio;
    private String phoneNumber;
}
