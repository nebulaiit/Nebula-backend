package com.nebula.Nebula.mapper;

import com.nebula.Nebula.dto.HeadingDropDownDto;
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
                .map(topic -> new TopicsDto(topic.getTopicName(), topic.getUrlSlug()))
                .collect(Collectors.toList());

        return HeadingDto.builder()
                .id(heading.getId())
                .headingName(heading.getHeadingName())
                .topics(topicDTOs)
                .build();
    }
    public HeadingDropDownDto toDropdownDto(Heading heading) {

        if (heading == null) {
            return null;
        }

        List<TopicsDto> topicDTOs = heading.getTopics().stream()
                .map(topic -> new TopicsDto(topic.getTopicName(), topic.getUrlSlug()))
                .collect(Collectors.toList());

        return HeadingDropDownDto.builder()
                .id(heading.getId())
                .headingName(heading.getHeadingName())

                .build();
    }

}
