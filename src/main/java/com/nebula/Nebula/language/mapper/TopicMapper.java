package com.nebula.Nebula.language.mapper;

import com.nebula.Nebula.language.dto.TopicResponseDto;
import com.nebula.Nebula.language.entity.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    public  TopicResponseDto toDto(Topic topic) {
        TopicResponseDto dto = new TopicResponseDto();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setSlug(topic.getSlug());
        dto.setOrderIndex(topic.getOrderIndex());
        return dto;
    }
}
