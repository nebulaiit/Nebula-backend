package com.nebula.Nebula.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.HeadingDto;
import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.service.HeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HeadingController {


    @Autowired
    private HeadingService headingService;


    @GetMapping("/heading")
    public ResponseEntity<List<HeadingDto>> getAllHeading(){
        List<HeadingDto> headingDtos = headingService.getAllHeading();

        return new ResponseEntity<>(headingDtos, HttpStatus.OK);
    }

    @GetMapping("/tutorial/{tutorialName}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable String tutorialName){

        Tutorial tutorial = headingService.getTutorial(tutorialName);

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/add-heading")
    public ResponseEntity<ResponseBodyDto> addHeadding(@RequestBody Heading heading){
        ResponseBodyDto responseBodyDto = headingService.addHeading(heading);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }


}
