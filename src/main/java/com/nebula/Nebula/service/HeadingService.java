package com.nebula.Nebula.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;

import com.nebula.Nebula.dto.HeadingDto;
import com.nebula.Nebula.mapper.HeadingMapper;
import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.repository.HeadingRepo;
import com.nebula.Nebula.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HeadingService {


    @Autowired
    private HeadingRepo headingRepo;

    @Autowired
    private TutorialRepo tutorialRepo;

    @Autowired
    private HeadingMapper headingMapper;



    public List<HeadingDto> getAllHeading() {
        List<Heading> headings = headingRepo.findAll();

        return headings.stream().map(headingMapper::toDto).collect(Collectors.toList());
    }

    public List<HeadingDto> getHeadingByTutorialName(String tutorialName) {
        Tutorial tutorial = tutorialRepo.findByTutorialName(tutorialName);
        List<Heading> headings = tutorial.getHeading();
        return headings.stream().map(headingMapper::toDto).collect(Collectors.toList());
    }

    public ResponseBodyDto addHeading(UUID id, Heading heading) {

        Tutorial tutorial = tutorialRepo.findById(id).orElse(null);

        Heading heading2 =  Heading.builder()
                .headingName(heading.getHeadingName())
                .tutorial(tutorial)
                .build();


        headingRepo.save(heading2);

        return ResponseBodyDto.builder().code(201).message("Heading has been Created").build();

    }



}
