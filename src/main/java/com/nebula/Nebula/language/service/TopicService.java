package com.nebula.Nebula.language.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.TopicRequestDto;
import com.nebula.Nebula.language.dto.TopicResponseDto;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    ResponseBodyDto createTopic(TopicRequestDto dto);
    List<TopicResponseDto> getTopicsByLanguage(UUID languageId);
    TopicResponseDto getTopicById(UUID id);
    ResponseBodyDto updateTopic(UUID id, TopicRequestDto dto);
    ResponseBodyDto deleteTopic(UUID id);
}
