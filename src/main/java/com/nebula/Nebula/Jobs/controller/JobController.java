package com.nebula.Nebula.Jobs.controller;

import com.nebula.Nebula.Jobs.dto.JobDetailsDto;
import com.nebula.Nebula.Jobs.dto.JobVacancyDto;
import com.nebula.Nebula.Jobs.model.JobVacancy;
import com.nebula.Nebula.Jobs.service.JobService;
import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<JobVacancyDto>> getAllJobs() {

        List<JobVacancyDto> jobs = jobService.getAllJobs();

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    @PostMapping("/add-vacancy")
    public ResponseEntity<ResponseBodyDto> createJob(@RequestBody JobVacancy job) {

       ResponseBodyDto responseBodyDto = jobService.createJobs(job);

       return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

    @GetMapping("/job-details/{id}")
    public ResponseEntity<JobDetailsDto> getJobDetailsById(@PathVariable UUID id){

        JobDetailsDto jobDetailsDto = jobService.getJobDetailsById(id);

        return new ResponseEntity<>(jobDetailsDto, HttpStatus.OK);

    }
}
