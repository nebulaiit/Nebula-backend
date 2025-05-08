package com.nebula.Nebula.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TutorialSignupRequest {

    private String name;
    private String email;
    private CharSequence password;
}
