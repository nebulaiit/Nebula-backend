package com.nebula.Nebula.Jobs.controller;

import com.nebula.Nebula.Jobs.dto.CompanyDto;
import com.nebula.Nebula.Jobs.dto.CompanyRequestDto;
import com.nebula.Nebula.Jobs.service.CompanyService;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companyDtos =companyService.getAllCompanies();
        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseBodyDto> createCompany(@RequestBody CompanyRequestDto company) {
        ResponseBodyDto saved = companyService.createCompany(company);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
