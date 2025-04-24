package com.nebula.Nebula.mapper;

import com.nebula.Nebula.dto.HeadingDto;
import com.nebula.Nebula.dto.TopicsDto;
import com.nebula.Nebula.model.Heading;
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
                .map(topic -> new TopicsDto(topic.getTopicName()))
                .collect(Collectors.toList());

        return HeadingDto.builder()
                .headingName(heading.getHeadingName())
                .topics(topicDTOs)
                .build();
    }
}
