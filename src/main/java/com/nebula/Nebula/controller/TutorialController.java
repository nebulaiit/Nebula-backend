package com.nebula.Nebula.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.TutorialDto;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;


    @GetMapping("/tutorial")
    public ResponseEntity<List<TutorialDto>> getAllTutorial() {

        List<TutorialDto> tutorialDto = tutorialService.getAllTutorial();

        return new ResponseEntity<>(tutorialDto, HttpStatus.OK);
    }

    @GetMapping("/tutorial/{tutorialName}")
    public ResponseEntity<Tutorial> getTutorial(@PathVariable String tutorialName) {

        Tutorial tutorial = tutorialService.getTutorial(tutorialName);

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorial")
    public ResponseEntity<ResponseBodyDto> createTutorial(@RequestBody Tutorial tutorial) {

        ResponseBodyDto responseBodyDto = tutorialService.createTutorial(tutorial);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }


}
