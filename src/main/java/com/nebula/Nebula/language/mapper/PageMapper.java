package com.nebula.Nebula.language.mapper;


import com.nebula.Nebula.language.dto.PageRequestDto;
import com.nebula.Nebula.language.dto.PageResponseDto;
import com.nebula.Nebula.language.entity.Image;
import com.nebula.Nebula.language.entity.Page;
import com.nebula.Nebula.language.entity.Topic;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PageMapper {

    public  Page toEntity(PageRequestDto dto, Topic topic) {
        Page page = new Page();
        page.setTopic(topic);
        page.setTitle(dto.getTitle());
        page.setContentHtml(dto.getContentHtml());
        return page;
    }

    public  PageResponseDto toDto(Page page) {
        return PageResponseDto.builder()
                .id(page.getId())
                .topicId(page.getTopic() != null ? page.getTopic().getId() : null)
                .topicName(page.getTopic() != null ? page.getTopic().getName() : null) // Optional
                .title(page.getTitle())
                .orderIndex(page.getOrderIndex())
                .contentHtml(page.getContentHtml())
                .imageUrls(page.getImages() != null
                        ? page.getImages().stream()
                        .filter(img -> img != null && img.getImageUrl() != null) // filter out nulls
                        .map(Image::getImageUrl)
                        .collect(Collectors.toList())
                        : List.of())
                .build();
    }
}

