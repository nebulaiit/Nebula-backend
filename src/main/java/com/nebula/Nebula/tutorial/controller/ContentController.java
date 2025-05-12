package com.nebula.Nebula.tutorial.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.dto.ContentBlockDTO;
import com.nebula.Nebula.tutorial.dto.CreateContentRequest;
import com.nebula.Nebula.tutorial.service.ContentService;
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
    public ResponseEntity<List<ContentBlockDTO>> getContentByTopicName(@PathVariable String urlSlug){
        List<ContentBlockDTO> contents = contentService.getContentByTopicName(urlSlug);

        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @PostMapping("/content/{id}")
    public ResponseEntity<ResponseBodyDto> createContent(@PathVariable UUID id, @RequestBody CreateContentRequest request){

        ResponseBodyDto responseBodyDto = contentService.createContent(id, request);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

//    @PutMapping("/update/content/{id}")
//    public ResponseEntity<ResponseBodyDto> updateContent(@PathVariable UUID id, @RequestBody ContentBlock contentBlock){
//
//        ResponseBodyDto responseBodyDto = contentService.updateContent(id, contentBlock);
//
//        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
//    }




}
