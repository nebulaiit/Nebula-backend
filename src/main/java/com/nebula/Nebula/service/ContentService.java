package com.nebula.Nebula.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.auth.repo.ContentRepo;
import com.nebula.Nebula.model.Content;
import com.nebula.Nebula.model.Topics;
import com.nebula.Nebula.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContentService {

    @Autowired
    private TopicsRepo topicsRepo;

    @Autowired
    private ContentRepo contentRepo;

    public List<Content> getContentByTopicName(String urlSlug) {

      Topics topics= topicsRepo.findByUrlSlug(urlSlug);
        return  topics.getContents();
    }

    public ResponseBodyDto createContent(UUID id, Content content) {

        Topics topics = topicsRepo.findById(id).orElse(null);

        Content content1 = Content.builder()
                .topics(topics)
                .contentHeading(content.getContentHeading())
                .content(content.getContent())
                .build();

        contentRepo.save(content1);

        return ResponseBodyDto.builder().code(201).message("Content Has Been Created").build();
    }
}
