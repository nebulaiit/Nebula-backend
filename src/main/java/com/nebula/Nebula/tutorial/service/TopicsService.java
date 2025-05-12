package com.nebula.Nebula.tutorial.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;

import com.nebula.Nebula.tutorial.dto.TopicsDropDownDto;
import com.nebula.Nebula.tutorial.model.Heading;
import com.nebula.Nebula.tutorial.model.Topics;
import com.nebula.Nebula.tutorial.model.Tutorial;
import com.nebula.Nebula.tutorial.repository.HeadingRepo;
import com.nebula.Nebula.tutorial.repository.TopicsRepo;
import com.nebula.Nebula.tutorial.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TopicsService {

    @Autowired
    private TopicsRepo topicsRepo;

    @Autowired
    private HeadingRepo headingRepo;

    @Autowired
    private TutorialRepo tutorialRepo;


    public ResponseBodyDto createTopics(UUID id, Topics topics) {

        Heading heading = headingRepo.findById(id).orElse(null);

        Topics topics1 = Topics.builder()
                .heading(heading)
                .topicName(topics.getTopicName())
                .urlSlug(topics.getUrlSlug())
                .build();

        topicsRepo.save(topics1);

        return  ResponseBodyDto.builder()
                .code(201)
                .message("Topics Has Been Created")
                .build();
    }


    public List<TopicsDropDownDto> getTopicByTutorialId(UUID id) {
        Tutorial tutorial = tutorialRepo.findById(id).orElse(null);

        if (tutorial == null || tutorial.getHeading() == null) {
            return List.of(); // return empty list if tutorial or headings are null
        }

        return tutorial.getHeading().stream()
                .filter(heading -> heading.getTopics() != null)
                .flatMap(heading -> heading.getTopics().stream())
                .map(topic -> TopicsDropDownDto.builder()
                        .topicName(topic.getTopicName())
                        .urlSlug(topic.getUrlSlug())
                        .build())
                .collect(Collectors.toList());
    }
}


