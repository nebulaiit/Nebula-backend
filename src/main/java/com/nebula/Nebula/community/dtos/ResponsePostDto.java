package com.nebula.Nebula.community.dtos;

import com.nebula.Nebula.community.model.Reply;
import com.nebula.Nebula.community.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostDto {

    private UUID id;
    private String content;
    private List<Tag> tags;
    private List<Reply> replies ;
    private Map<String, Integer> reactions = new HashMap<>();
}
