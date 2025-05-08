package com.nebula.Nebula.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.TopicsDropDownDto;
import com.nebula.Nebula.model.Topics;
import com.nebula.Nebula.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TopicsController {

    @Autowired
    private TopicsService topicsService;

    @PostMapping("/topics/{id}")
    public ResponseEntity<ResponseBodyDto> createTopics(@PathVariable UUID id, @RequestBody Topics topics){

        ResponseBodyDto responseBodyDto =  topicsService.createTopics(id, topics);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }

    @GetMapping("/tutorial/heading/{id}")
    public ResponseEntity<List<TopicsDropDownDto>> getTopicByTutorialId(@PathVariable UUID id){

        List<TopicsDropDownDto> topicDto = topicsService.getTopicByTutorialId(id);

        return new ResponseEntity<>(topicDto, HttpStatus.OK);
    }
}
