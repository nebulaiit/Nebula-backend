package com.nebula.Nebula.language.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.LanguageDto;
import com.nebula.Nebula.language.entity.Language;
import com.nebula.Nebula.language.mapper.LanguageMapper;
import com.nebula.Nebula.language.overviewdto.LanguageOverviewDTO;
import com.nebula.Nebula.language.repo.LanguageRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private LanguageMapper languageMapper;

    @Override
    public List<LanguageDto> getAllLanguages() {
        return languageRepo.findAll()
                .stream()
                .map(languageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseBodyDto createLanguage(LanguageDto lang) {

        if (languageRepo.existsByNameIgnoreCase(lang.getName())) {
            return ResponseBodyDto.builder()
                    .code(409)
                    .message("Language with this " + lang.getName() + " already exists.")
                    .build();
        }
        Language language = new Language();
        language.setName(lang.getName());
        language.setCategory(lang.getCategory());

        // Generate slug from name
        String slug = generateSlug(lang.getName());

        language.setSlug(slug);

        languageRepo.save(language);

        return ResponseBodyDto.builder().code(201).message("Language Created Successfully").build();
    }

    @Override
    public LanguageDto getLanguageBySlug(String slug) {
        Language language = languageRepo.findBySlug(slug);

        return languageMapper.toDto(language);
    }

    @Override
    public ResponseBodyDto updateLanguage(UUID id, LanguageDto dto) {
        Language language = languageRepo.findById(id).orElse(null);

        if (language == null){
            return ResponseBodyDto.builder()
                    .code(400)
                    .message("Unable to find language with name" + dto.getName())
                    .build();
        }

        language.setName(dto.getName());
        language.setSlug(dto.getSlug());
        language.setCategory(dto.getCategory());

        languageRepo.save(language);
        return ResponseBodyDto.builder().code(200).message("Language Updated Successfully").build();

    }

    @Override
    public ResponseBodyDto deleteLanguage(UUID id) {
        languageRepo.deleteById(id);
        return ResponseBodyDto.builder().code(200).message("language Successfully Deleted").build();
    }

    @Override
    public LanguageOverviewDTO getLanguageOverview(String slug) {
        Language language = languageRepo.findBySlug(slug);

        return languageMapper.toOverviewDTO(language);
    }


    private String generateSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("");
        slug = slug.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
        return slug;
    }
}
