package com.nebula.Nebula.language.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.TopicRequestDto;
import com.nebula.Nebula.language.dto.TopicResponseDto;
import com.nebula.Nebula.language.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<ResponseBodyDto> createTopic(@RequestBody TopicRequestDto dto) {
        ResponseBodyDto responseBodyDto = topicService.createTopic(dto);
        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

    @GetMapping("/by-language/{languageId}")
    public ResponseEntity<List<TopicResponseDto>> getByLanguage(@PathVariable UUID languageId) {
        List<TopicResponseDto> topicResponseDtos = topicService.getTopicsByLanguage(languageId);

        return new ResponseEntity<>(topicResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> getById(@PathVariable UUID id) {

        TopicResponseDto topicResponseDto = topicService.getTopicById(id);

        return new ResponseEntity<>(topicResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> update(@PathVariable UUID id, @RequestBody TopicRequestDto dto) {

        ResponseBodyDto responseBodyDto = topicService.updateTopic(id, dto);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> delete(@PathVariable UUID id) {
        ResponseBodyDto responseBodyDto = topicService.deleteTopic(id);
        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
