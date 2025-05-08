package com.nebula.Nebula.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebula.Nebula.dto.ContentBlockDTO;

import com.nebula.Nebula.model.ContentBlock;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ContentMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ContentBlockDTO toDTO(ContentBlock block) {

        Map<String, Object> extra = Collections.emptyMap();
        if (block.getExtraJson() != null) {
            try {
                extra = objectMapper.readValue(block.getExtraJson(), new TypeReference<>() {});
            } catch (Exception e) {
                e.printStackTrace(); // Consider proper logging here
            }
        }

        return new ContentBlockDTO(
                block.getId(),
                block.getType(),
                block.getValue(),
                extra
        );
    }

    public ContentBlock toEntity(ContentBlockDTO dto, int orderIndex) {
        return ContentBlock.builder()
                .type(dto.getType())
                .value(dto.getValue())
                .orderIndex(orderIndex)
                .extraJson(writeExtraJson(dto.getExtra()))
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
