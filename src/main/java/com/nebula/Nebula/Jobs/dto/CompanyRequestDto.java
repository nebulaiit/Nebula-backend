package com.nebula.Nebula.Jobs.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequestDto {
    private String name;
    private String email;
    private String website;
    private String phone;
    private String address;
}
