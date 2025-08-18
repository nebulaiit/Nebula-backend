package com.nebula.Nebula.language.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.LanguageDto;
import com.nebula.Nebula.language.overviewdto.LanguageOverviewDTO;
import com.nebula.Nebula.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping
    public ResponseEntity<ResponseBodyDto> create(@RequestBody LanguageDto lang) {

        ResponseBodyDto responseBodyDto = languageService.createLanguage(lang);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {

        List<LanguageDto> languageDtos = languageService.getAllLanguages();

        return new ResponseEntity<>(languageDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> updateLanguage(@PathVariable UUID id, @RequestBody LanguageDto dto) {

        ResponseBodyDto responseBodyDto = languageService.updateLanguage(id, dto);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<LanguageDto> getLanguageBySlug(@PathVariable String slug) {

        LanguageDto languageDto = languageService.getLanguageBySlug(slug);

        return new ResponseEntity<>(languageDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBodyDto> deleteLanguage(@PathVariable UUID id) {

        ResponseBodyDto responseBodyDto = languageService.deleteLanguage(id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }

    @GetMapping("/overview/{slug}")
    public ResponseEntity<LanguageOverviewDTO> getOverview(@PathVariable String slug) {

        LanguageOverviewDTO languageOverviewDTO = languageService.getLanguageOverview(slug);

        return new ResponseEntity<>(languageOverviewDTO, HttpStatus.OK);
    }

}

