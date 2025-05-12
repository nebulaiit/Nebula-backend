package com.nebula.Nebula.tutorial.service;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.dto.TutorialDto;
import com.nebula.Nebula.tutorial.mapper.TutorialMapper;
import com.nebula.Nebula.tutorial.model.Heading;
import com.nebula.Nebula.tutorial.model.Tutorial;
import com.nebula.Nebula.tutorial.repository.TutorialRepo;
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

    public TutorialDto createTutorial(Tutorial tutorial) {

         Tutorial tutorial1 =   tutorialRepo.save(tutorial);

            return TutorialDto.builder()
                    .id(tutorial1.getId())
                    .tutorialName(tutorial1.getTutorialName())
                    .build();
    }

    public ResponseBodyDto updateTutorial(UUID id, Tutorial tutorial) {

        Tutorial tutorial1 = tutorialRepo.findById(id).orElse(null);

        if (tutorial1 !=null){
            tutorial1.setTutorialName(tutorial.getTutorialName());
            tutorialRepo.save(tutorial1);

        }

        return ResponseBodyDto.builder().code(201).message("Tutorial has been Updated Successfully").build();

    }

    public ResponseBodyDto deleteTutorial(UUID id) {

        tutorialRepo.deleteById(id);

        return ResponseBodyDto.builder().code(200).message("Tutorial Has Been Deleted Successfully").build();
    }

    public TutorialDto addTutorial(Tutorial tutorial) {
        // Check for existing tutorial
        Tutorial existingTutorial = tutorialRepo.findByTutorialName(tutorial.getTutorialName());

        if (existingTutorial != null) {
            throw new RuntimeException("Tutorial already exists with name: " + tutorial.getTutorialName());
        }

        // Set relationships
        if (tutorial.getHeading() != null) {
            tutorial.getHeading().forEach(heading -> {
                heading.setTutorial(tutorial);

                if (heading.getTopics() != null) {
                    heading.getTopics().forEach(topic -> {
                        topic.setHeading(heading);

                        if (topic.getContents() != null) {
                            topic.getContents().forEach(content -> {
                                content.setTopics(topic);

                                if (content.getBlocks() != null) {
                                    content.getBlocks().forEach(block -> block.setContent(content));
                                }
                            });
                        }
                    });
                }
            });
        }

        Tutorial savedTutorial = tutorialRepo.save(tutorial);

        // Map to DTO (you can use ModelMapper or manual mapping)
        return tutorialMapper.toDto(savedTutorial);
    }

}
