package com.nebula.Nebula.service;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.TutorialDto;
import com.nebula.Nebula.mapper.TutorialMapper;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepo tutorialRepo;

    @Autowired
    private TutorialMapper tutorialMapper;

    public Tutorial getTutorial(String tutorialName) {
        return tutorialRepo.findByTutorialName(tutorialName);
    }

    public List<TutorialDto> getAllTutorial() {

        List<Tutorial> tutorial = tutorialRepo.findAll();

        return tutorial.stream().map(tutorialMapper::toDto).collect(Collectors.toList());
    }

    public ResponseBodyDto createTutorial(Tutorial tutorial) {

        if (tutorial != null){
            tutorialRepo.save(tutorial);

            return ResponseBodyDto.builder()
                    .code(201)
                    .message("Tutorial Has Been Created")
                    .build();
        }

        return ResponseBodyDto.builder()
                .code(400)
                .message("Error While Creating Tutorial")
                .build();
    }

    public ResponseBodyDto updateTutorial(UUID id, Tutorial tutorial) {

        Tutorial tutorial1 = tutorialRepo.findById(id).orElse(null);

        if (tutorial1 !=null){
            tutorial1.setTutorialName(tutorial.getTutorialName());
            tutorialRepo.save(tutorial1);

        }

        return ResponseBodyDto.builder().code(201).message("Heading has been Updated").build();

    }
}
