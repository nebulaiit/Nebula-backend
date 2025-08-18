package com.nebula.Nebula.language.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.language.dto.PageRequestDto;
import com.nebula.Nebula.language.dto.PageResponseDto;
import com.nebula.Nebula.language.entity.Image;
import com.nebula.Nebula.language.entity.Page;
import com.nebula.Nebula.language.entity.Topic;
import com.nebula.Nebula.language.repo.PageRepo;
import com.nebula.Nebula.language.repo.TopicRepo;
import com.nebula.Nebula.language.mapper.PageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private PageRepo pageRepo;

    @Autowired
    private PageMapper pageMapper;


    @Override
    public ResponseBodyDto createPage(PageRequestDto dto) {
        Topic topic = topicRepo.findById(dto.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        int nextOrderIndex = pageRepo.findByTopic(topic).size() + 1;

        Page page = Page.builder()
                .title(dto.getTitle())
                .slug(generateSlug(dto.getTitle()))
                .contentHtml(dto.getContentHtml())
                .orderIndex(nextOrderIndex)
                .topic(topic)
                .build();

        // Save images if any
        if (dto.getImageUrls() != null) {
            List<Image> images = dto.getImageUrls().stream()
                    .map(url -> Image.builder().imageUrl(url).page(page).build())
                    .collect(Collectors.toList());
            page.setImages(images);
        }

        pageRepo.save(page);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Page created successfully")
                .build();
    }

    @Override
    public PageResponseDto getPageById(UUID id) {
        Page page = pageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));
        return pageMapper.toDto(page);
    }

    @Override
    public List<PageResponseDto> getPagesByTopicSlug(String slug) {
        return pageRepo.findByTopicSlug(slug).stream()
                .map(pageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseBodyDto updatePage(UUID id, PageRequestDto dto) {
        Page page = pageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        page.setTitle(dto.getTitle());
        page.setSlug(generateSlug(dto.getTitle()));
        page.setContentHtml(dto.getContentHtml());

        // Update images
        if (dto.getImageUrls() != null) {
            page.getImages().clear();
            List<Image> images = dto.getImageUrls().stream()
                    .map(url -> Image.builder().imageUrl(url).page(page).build())
                    .toList();
            page.getImages().addAll(images);
        }

        pageRepo.save(page);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Page updated successfully")
                .build();
    }

    @Override
    public ResponseBodyDto deletePage(UUID id) {
        Page page = pageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Page not found"));

        pageRepo.delete(page);

        return ResponseBodyDto.builder()
                .code(200)
                .message("Page deleted successfully")
                .build();
    }

    private String generateSlug(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String slug = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
                .matcher(normalized)
                .replaceAll("");
        return slug.toLowerCase().replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
