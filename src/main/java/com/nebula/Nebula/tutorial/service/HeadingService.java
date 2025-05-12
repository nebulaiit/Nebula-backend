package com.nebula.Nebula.tutorial.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;

import com.nebula.Nebula.tutorial.dto.HeadingDto;
import com.nebula.Nebula.tutorial.dto.HeadingsDto;
import com.nebula.Nebula.tutorial.mapper.HeadingMapper;
import com.nebula.Nebula.tutorial.model.Heading;
import com.nebula.Nebula.tutorial.model.Tutorial;
import com.nebula.Nebula.tutorial.repository.HeadingRepo;
import com.nebula.Nebula.tutorial.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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



    public List<HeadingsDto> getAllHeading() {
        List<Heading> headings = headingRepo.findAll();

        return headings.stream().map(headingMapper::toDtos).collect(Collectors.toList());
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


    public ResponseBodyDto updateHeading(UUID id, Heading heading) {

        Heading heading1 = headingRepo.findById(id).orElse(null);

        if (heading1 !=null){
            heading1.setHeadingName(heading.getHeadingName());
            headingRepo.save(heading1);
        }

        return ResponseBodyDto.builder().code(201).message("Heading has been Updated").build();

    }
}
