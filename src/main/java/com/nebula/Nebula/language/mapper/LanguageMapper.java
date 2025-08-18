package com.nebula.Nebula.language.mapper;

import com.nebula.Nebula.language.dto.LanguageDto;
import com.nebula.Nebula.language.entity.Language;
import com.nebula.Nebula.language.overview.*;
import com.nebula.Nebula.language.overviewdto.CommunityLinkDTO;
import com.nebula.Nebula.language.overviewdto.LanguageOverviewDTO;
import com.nebula.Nebula.language.overviewdto.ProjectDTO;
import com.nebula.Nebula.language.overviewdto.RoadmapStepDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LanguageMapper {

    public LanguageDto toDto(Language language) {

        return  LanguageDto.builder()
                .id(language.getId())
                .name(language.getName())
                .slug(language.getSlug())
                .category(language.getCategory())
                .build();
    }

    public LanguageOverviewDTO toOverviewDTO(Language language) {
        if (language == null) {
            return null;
        }

        return LanguageOverviewDTO.builder()
                .id(language.getId())
                .name(language.getName())
                .slug(language.getSlug())
                .briefHistory(language.getBriefHistory())
                .realLifeUses(language.getRealLifeUses()
                        .stream()
                        .map(RealLifeUse::getDescription)
                        .collect(Collectors.toList()))
                .useCases(language.getUseCases()
                        .stream()
                        .map(UseCase::getDescription)
                        .collect(Collectors.toList()))
                .careerPaths(language.getCareerPaths()
                        .stream()
                        .map(CareerPath::getTitle)
                        .collect(Collectors.toList()))
                .frameworks(language.getFrameworks()
                        .stream()
                        .map(Framework::getName)
                        .collect(Collectors.toList()))
                .libraries(language.getLibraries()
                        .stream()
                        .map(Library::getName)
                        .collect(Collectors.toList()))
                .companies(language.getCompanies()
                        .stream()
                        .map(CompanyName::getName)
                        .collect(Collectors.toList()))
                .roadmap(language.getRoadmap()
                        .stream()
                        .map(r -> new RoadmapStepDTO(r.getLevel(), r.getDetails()))
                        .collect(Collectors.toList()))
                .communityLinks(language.getCommunityLinks()
                        .stream()
                        .map(c -> new CommunityLinkDTO(c.getName(), c.getUrl()))
                        .collect(Collectors.toList()))
                .projects(language.getProjects()
                        .stream()
                        .map(p -> new ProjectDTO(p.getName(), p.getUrl()))
                        .collect(Collectors.toList()))
                .build();
    }
}
