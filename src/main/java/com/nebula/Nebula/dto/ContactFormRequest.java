package com.nebula.Nebula.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactFormRequest {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String message;

}