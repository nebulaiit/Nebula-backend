package com.nebula.Nebula.language.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.TopicRequestDto;
import com.nebula.Nebula.language.dto.TopicResponseDto;
import com.nebula.Nebula.language.entity.Language;
import com.nebula.Nebula.language.entity.Topic;
import com.nebula.Nebula.language.mapper.TopicMapper;
import com.nebula.Nebula.language.repo.LanguageRepo;
import com.nebula.Nebula.language.repo.TopicRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private LanguageRepo languageRepo;

    @Autowired
    private TopicMapper topicMapper;


    @Override
    public ResponseBodyDto createTopic(TopicRequestDto dto) {

        if (topicRepo.existsByNameIgnoreCase(dto.getName())) {
            return ResponseBodyDto.builder()
                    .code(409)
                    .message("Language with this " + dto.getName() + " already exists.")
                    .build();
        }
        Language language = languageRepo.findById(dto.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));

        int nextOrderIndex = topicRepo.findByLanguage(language).size() + 1;

        Topic topic = Topic.builder()
                .name(dto.getName())
                .slug(generateSlug(dto.getName()))
                .language(language)
                .orderIndex(nextOrderIndex)
                .build();

             topicRepo.save(topic);

             return ResponseBodyDto.builder().code(201).message("Topic Created Successfully").build();

    }

    @Override
    public List<TopicResponseDto> getTopicsByLanguage(UUID languageId) {

        List<Topic> topics = topicRepo.findByLanguageIdOrderByOrderIndexAsc(languageId);

       return topics.stream().map(topicMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TopicResponseDto getTopicById(UUID id) {
        Topic topic = topicRepo.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));

        return topicMapper.toDto(topic);
    }

    @Override
    public ResponseBodyDto updateTopic(UUID id, TopicRequestDto dto) {
        Topic topic = topicRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topic.setName(dto.getName());
        topic.setSlug(generateSlug(dto.getName()));

        topicRepo.save(topic);

        return ResponseBodyDto.builder().code(200).message("Topic Updated Successfully").build();

    }

    @Override
    public ResponseBodyDto deleteTopic(UUID id) {
        Topic topic = topicRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topicRepo.delete(topic);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Topic deleted successfully")
                .build();
    }

    private String generateSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("");
        slug = slug.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
        return slug;
    }
}
