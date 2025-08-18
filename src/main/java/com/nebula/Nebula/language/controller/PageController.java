package com.nebula.Nebula.language.controller;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.PageRequestDto;
import com.nebula.Nebula.language.dto.PageResponseDto;
import com.nebula.Nebula.language.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pages")
@RequiredArgsConstructor
public class PageController {

    @Autowired
    private  PageService pageService;

    @PostMapping
    public ResponseEntity<ResponseBodyDto> createPage(@RequestBody PageRequestDto dto) {

        ResponseBodyDto responseBodyDto = pageService.createPage(dto);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PageResponseDto> getPageById(@PathVariable UUID id) {
        PageResponseDto pageResponseDto = pageService.getPageById(id);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @GetMapping("/topic/{slug}")
    public ResponseEntity<List<PageResponseDto>> getPagesByTopic(@PathVariable String slug) {

        List<PageResponseDto> pageResponseDto = pageService.getPagesByTopicSlug(slug);

        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> updatePage(@PathVariable UUID id, @RequestBody PageRequestDto dto) {
        ResponseBodyDto responseBodyDto = pageService.updatePage(id ,dto);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deletePage(@PathVariable UUID id) {

        ResponseBodyDto responseBodyDto = pageService.deletePage(id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }
}
