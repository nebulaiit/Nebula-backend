package com.nebula.Nebula.tutorial.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.model.ReferenceContent;
import com.nebula.Nebula.tutorial.model.Tutorial;
import com.nebula.Nebula.tutorial.repository.ReferenceRepo;
import com.nebula.Nebula.tutorial.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReferenceService {

    @Autowired
    private ReferenceRepo referenceRepo;

    @Autowired
    private TutorialRepo tutorialRepo;

    public ReferenceContent getReference(String tutorialName) {
        Tutorial tutorial = tutorialRepo.findByTutorialName(tutorialName);

        return tutorial.getReferenceContent();

    }

    public ResponseBodyDto createReferenceContent(UUID id, ReferenceContent referenceContent) {

        Tutorial tutorial = tutorialRepo.findById(id).orElse(null);

        ReferenceContent referenceContent1 = ReferenceContent.builder()
                                            .referenceTitle(referenceContent.getReferenceTitle())
                                            .referencePara(referenceContent.getReferencePara())
                                            .tutorial(tutorial)
                                            .build();

        referenceRepo.save(referenceContent1);

        return ResponseBodyDto.builder()
                              .code(201)
                              .message("Reference Content Has Been Created")
                              .build();
    }
}