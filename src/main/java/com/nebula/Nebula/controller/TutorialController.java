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
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;


    @GetMapping("/tutorial")  // /api/tutorial
    public ResponseEntity<List<TutorialDto>> getAllTutorial() {

        List<TutorialDto> tutorialDto = tutorialService.getAllTutorial();

        return new ResponseEntity<>(tutorialDto, HttpStatus.OK);
    }

    @GetMapping("/tutorial/{tutorialName}") // /api/tutorial/Java
    public ResponseEntity<Tutorial> getTutorial(@PathVariable String tutorialName) {

        Tutorial tutorial = tutorialService.getTutorial(tutorialName);

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorial") // /api/tutorial
    public ResponseEntity<ResponseBodyDto> createTutorial(@RequestBody Tutorial tutorial) {

        ResponseBodyDto responseBodyDto = tutorialService.createTutorial(tutorial);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }

    @PutMapping("/tutorial/{id}")
    public ResponseEntity<ResponseBodyDto> updatingTutorial(@PathVariable UUID id, @RequestBody Tutorial tutorial){

        ResponseBodyDto responseBodyDto = tutorialService.updateTutorial(id,tutorial);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

}
