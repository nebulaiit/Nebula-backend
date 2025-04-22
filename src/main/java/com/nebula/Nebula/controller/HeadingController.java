package com.nebula.Nebula.controller;

import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.service.HeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HeadingController {


    @Autowired
    private HeadingService headingService;


    @GetMapping("/heading")
    public ResponseEntity<List<Heading>> getAllHeading(){
        List<Heading> headings = headingService.getAllHeading();

        return new ResponseEntity<>(headings, HttpStatus.OK);
    }
}
