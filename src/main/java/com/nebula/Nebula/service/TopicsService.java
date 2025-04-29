package com.nebula.Nebula.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.model.Topics;
import com.nebula.Nebula.repository.HeadingRepo;
import com.nebula.Nebula.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopicsService {

    @Autowired
    private TopicsRepo topicsRepo;

    @Autowired
    private HeadingRepo headingRepo;

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
}


