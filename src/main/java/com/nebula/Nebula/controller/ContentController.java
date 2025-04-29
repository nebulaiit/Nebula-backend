package com.nebula.Nebula.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.model.Content;
import com.nebula.Nebula.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/content/{id}")
    public ResponseEntity<ResponseBodyDto> createContent(@PathVariable UUID id, @RequestBody Content content){

        ResponseBodyDto responseBodyDto = contentService.createContent(id, content);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }




}
