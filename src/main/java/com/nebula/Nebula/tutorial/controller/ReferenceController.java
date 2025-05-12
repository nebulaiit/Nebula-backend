package com.nebula.Nebula.tutorial.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.tutorial.model.ReferenceContent;
import com.nebula.Nebula.tutorial.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService ;

    @GetMapping("/reference/{tutorialName}")
    public ResponseEntity<ReferenceContent> getMapping(@PathVariable String tutorialName){
        ReferenceContent referenceContent= referenceService.getReference(tutorialName);
        return new ResponseEntity<>(referenceContent, HttpStatus.OK);

    }

    @PostMapping("/reference/{id}")
    public  ResponseEntity<ResponseBodyDto> createReferenceContent(@PathVariable UUID id, @RequestBody ReferenceContent referenceContent){

        ResponseBodyDto responseBodyDto = referenceService.createReferenceContent(id, referenceContent);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

}