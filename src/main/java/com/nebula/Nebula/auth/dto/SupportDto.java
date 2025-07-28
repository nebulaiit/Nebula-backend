package com.nebula.Nebula.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupportDto {

    private UUID id;
    private String empId;
    private String name;
    private String email;
    private String subjectLine;
    private String message;
}