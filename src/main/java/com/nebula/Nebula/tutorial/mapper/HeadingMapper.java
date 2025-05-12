package com.nebula.Nebula.tutorial.mapper;

import com.nebula.Nebula.tutorial.dto.HeadingDto;
import com.nebula.Nebula.tutorial.dto.HeadingsDto;
import com.nebula.Nebula.tutorial.dto.TopicsDto;
import com.nebula.Nebula.tutorial.model.Heading;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HeadingMapper {


    public HeadingDto toDto(Heading heading) {

        if (heading == null) {
            return null;
        }

        List<TopicsDto> topicDTOs = heading.getTopics().stream()
                .map(topic -> new TopicsDto(topic.getId(),topic.getTopicName(), topic.getUrlSlug()))
                .collect(Collectors.toList());

        return HeadingDto.builder()
                .id(heading.getId())
                .headingName(heading.getHeadingName())
                .orderIndex(heading.getOrderIndex())
                .topics(topicDTOs)
                .build();
    }

    public HeadingsDto toDtos(Heading heading){

        if (heading == null) {
            return null;
        }

        return HeadingsDto.builder()
                .id(heading.getId())
                .headingName(heading.getHeadingName())
                .build();
    }


}
