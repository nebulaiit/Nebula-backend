package com.nebula.Nebula.tutorial.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebula.Nebula.tutorial.dto.ContentBlockDTO;

import com.nebula.Nebula.tutorial.model.ContentBlock;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ContentMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ContentBlockDTO toDTO(ContentBlock block) {

        Map<String, Object> extra = Collections.emptyMap();


        return new ContentBlockDTO(
                block.getId(),
                block.getType(),
                block.getValue()
        );
    }

    public ContentBlock toEntity(ContentBlockDTO dto, int orderIndex) {
        return ContentBlock.builder()
                .type(dto.getType())
                .value(dto.getValue())
                .orderIndex(orderIndex)

                .build();
    }

    private String writeExtraJson(Object extra) {
        try {
            return objectMapper.writeValueAsString(extra);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    private Map<String, Object> parseExtraJson(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            return null;
        }
    }

}
