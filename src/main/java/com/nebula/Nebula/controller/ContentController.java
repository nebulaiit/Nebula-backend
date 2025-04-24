package com.nebula.Nebula.controller;

import com.nebula.Nebula.model.Content;
import com.nebula.Nebula.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/content/{urlSlug}")
    public ResponseEntity<List<Content>> getContentByTopicName(@PathVariable String urlSlug){
        List<Content> contents = contentService.getContentByTopicName(urlSlug);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }




}
