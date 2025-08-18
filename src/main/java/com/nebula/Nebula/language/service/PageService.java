package com.nebula.Nebula.language.service;


import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.PageRequestDto;
import com.nebula.Nebula.language.dto.PageResponseDto;

import java.util.List;
import java.util.UUID;

public interface PageService {
    ResponseBodyDto createPage(PageRequestDto dto);
    PageResponseDto getPageById(UUID id);
    List<PageResponseDto> getPagesByTopicSlug(String slug);
    ResponseBodyDto updatePage(UUID id, PageRequestDto dto);
    ResponseBodyDto deletePage(UUID id);
}
