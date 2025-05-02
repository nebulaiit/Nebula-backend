package com.nebula.Nebula.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.HeadingDropDownDto;
import com.nebula.Nebula.dto.HeadingDto;
import com.nebula.Nebula.dto.TutorialDto;
import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.service.HeadingService;
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
    public ResponseEntity<List<HeadingDto>> getAllHeading(){
        List<HeadingDto> headingDtos = headingService.getAllHeading();

        return new ResponseEntity<>(headingDtos, HttpStatus.OK);
    }

    @GetMapping("/tutorial/heading/{id}")
    public ResponseEntity<List<HeadingDropDownDto>> getHeadingByTutorialId(@PathVariable UUID id){

        List<HeadingDropDownDto> headingDto = headingService.getHeadingByTutorialId(id);

        return new ResponseEntity<>(headingDto, HttpStatus.OK);
    }

    @GetMapping("/heading/{tutorialName}")
    public ResponseEntity<List<HeadingDto>> getHeadingById(@PathVariable String tutorialName){

        List<HeadingDto> headingDto = headingService.getHeadingById(tutorialName);

        return new ResponseEntity<>(headingDto, HttpStatus.OK);
    }

    @PostMapping("/add-heading/{id}")
    public ResponseEntity<ResponseBodyDto> addHeadding(@PathVariable UUID id,  @RequestBody Heading heading){
        ResponseBodyDto responseBodyDto = headingService.addHeading(id, heading);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }


}
