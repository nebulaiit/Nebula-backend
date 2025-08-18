package com.nebula.Nebula.Jobs.service;

import com.nebula.Nebula.Jobs.dto.CompanyDto;
import com.nebula.Nebula.Jobs.dto.CompanyRequestDto;
import com.nebula.Nebula.Jobs.mapper.CompanyMapper;
import com.nebula.Nebula.Jobs.model.Company;
import com.nebula.Nebula.Jobs.repo.CompanyRepo;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyDto> getAllCompanies() {

        List<Company> companies = companyRepo.findAll();
        return companies.stream().map(companyMapper::toDto).collect(Collectors.toList());
    }

    public ResponseBodyDto createCompany(CompanyRequestDto company) {

        Company company1 = companyRepo.findByName(company.getName());

        if (company1 != null) {
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Duplicate Entry: A Company already exists .")
                    .build();
        }

        Company saveCompany = Company.builder()
                .name(company.getName())
                .address(company.getAddress())
                .email(company.getEmail())
                .phone(company.getPhone())
                .address(company.getAddress())
                .build();

        companyRepo.save(saveCompany);

        return ResponseBodyDto.builder()
                .code(201)
                .message("Company Details added successfully.")
                .build();
    }
}
