package com.nebula.Nebula.tutorial.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;

import com.nebula.Nebula.tutorial.dto.HeadingDto;
import com.nebula.Nebula.tutorial.dto.HeadingsDto;
import com.nebula.Nebula.tutorial.model.Heading;
import com.nebula.Nebula.tutorial.service.HeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class HeadingController {


    @Autowired
    private HeadingService headingService;


    @GetMapping("/heading")
    public ResponseEntity<List<HeadingsDto>> getAllHeading(){
        List<HeadingsDto> headingDtos = headingService.getAllHeading();

        return new ResponseEntity<>(headingDtos, HttpStatus.OK);
    }



    @GetMapping("/heading/{tutorialName}")
    public ResponseEntity<List<HeadingDto>> getHeadingById(@PathVariable String tutorialName){

        List<HeadingDto> headingDto = headingService.getHeadingByTutorialName(tutorialName);

        return new ResponseEntity<>(headingDto, HttpStatus.OK);
    }

    @PostMapping("/add-heading/{id}")
    public ResponseEntity<ResponseBodyDto> addHeading(@PathVariable UUID id,  @RequestBody Heading heading){
        ResponseBodyDto responseBodyDto = headingService.addHeading(id, heading);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }


    @PutMapping("/updating/{id}")
    public ResponseEntity<ResponseBodyDto> updateHeading(@PathVariable UUID id, @RequestBody Heading heading){

        ResponseBodyDto responseBodyDto = headingService.updateHeading(id, heading);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }

}
