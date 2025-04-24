package com.nebula.Nebula.service;

import com.nebula.Nebula.model.Content;
import com.nebula.Nebula.repository.TopicsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private TopicsRepo topicsRepo;


    public List<Content> getContentByTopicName(String urlSlug) {

        return topicsRepo.findByUrlSlug(urlSlug);

    }
}
