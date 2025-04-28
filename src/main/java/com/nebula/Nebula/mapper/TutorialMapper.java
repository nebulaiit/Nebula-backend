package com.nebula.Nebula.mapper;

import com.nebula.Nebula.dto.TutorialDto;
import com.nebula.Nebula.model.Tutorial;
import org.springframework.stereotype.Component;

@Component
public class TutorialMapper {

    public TutorialDto toDto(Tutorial tutorial) {

        if (tutorial == null) {
            return null;
        }

        return TutorialDto.builder()
                .id(tutorial.getId())
                .tutorialName(tutorial.getTutorialName())
                .build();

    }
}
