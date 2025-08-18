package com.nebula.Nebula.language.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.LanguageDto;
import com.nebula.Nebula.language.overviewdto.LanguageOverviewDTO;

import java.util.List;
import java.util.UUID;

public interface LanguageService {
    List<LanguageDto> getAllLanguages();
    ResponseBodyDto createLanguage(LanguageDto dto);
    ResponseBodyDto updateLanguage(UUID id, LanguageDto dto);
    LanguageDto getLanguageBySlug(String slug);
    ResponseBodyDto deleteLanguage(UUID id);
    LanguageOverviewDTO getLanguageOverview(String slug);
}