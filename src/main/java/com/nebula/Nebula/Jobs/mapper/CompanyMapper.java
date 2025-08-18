package com.nebula.Nebula.Jobs.mapper;

import com.nebula.Nebula.Jobs.dto.CompanyDto;
import com.nebula.Nebula.Jobs.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDto toDto(Company company) {

        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .location(company.getAddress())
                .build();
    }
}
