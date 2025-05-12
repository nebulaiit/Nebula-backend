package com.nebula.Nebula.tutorial.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.dto.ContentBlockDTO;
import com.nebula.Nebula.tutorial.dto.CreateContentRequest;
import com.nebula.Nebula.tutorial.mapper.ContentMapper;
import com.nebula.Nebula.tutorial.model.Content;
import com.nebula.Nebula.tutorial.model.ContentBlock;
import com.nebula.Nebula.tutorial.model.Topics;
import com.nebula.Nebula.tutorial.repository.ContentRepo;
import com.nebula.Nebula.tutorial.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentService {

    @Autowired
    private TopicsRepo topicsRepo;

    @Autowired
    private ContentRepo contentRepo;

    @Autowired
    private ContentMapper contentMapper;

    public List<ContentBlockDTO> getContentByTopicName(String urlSlug) {

        Optional<Content> contentOpt = contentRepo.findByTopicsUrlSlug(urlSlug);

        if (contentOpt.isEmpty()) return Collections.emptyList();

        List<ContentBlock> blocks = contentOpt.get().getBlocks();
        return blocks.stream()
                .map(contentMapper::toDTO)
                .toList();
    }

    public ResponseBodyDto createContent(UUID id, CreateContentRequest request) {

        Topics topics = topicsRepo.findById(id).orElse(null);

        List<ContentBlock> blocks = new ArrayList<>();

        for (int i = 0; i < request.getBlocks().size(); i++) {
            ContentBlock block = contentMapper.toEntity(request.getBlocks().get(i), i);
            blocks.add(block);
        }

        Content content = Content.builder()
                .contentHeading(request.getContentHeading())
                .topics(topics)
                .blocks(blocks)
                .build();

        blocks.forEach(block -> block.setContent(content));
        contentRepo.save(content);

        return ResponseBodyDto.builder().code(201).message("Content Has Been Created").build();
    }

//    public ResponseBodyDto updateContent(UUID id, ContentBlock contentBlock) {
//
//        Content content = contentRepo.findById(id).orElse(null);
//
//    }
}
