package com.nebula.Nebula.language.overviewdto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageOverviewDTO {

    private UUID id;
    private String name;
    private String slug;
    private String briefHistory;
    private List<String> realLifeUses;
    private List<String> useCases;
    private List<String> careerPaths;
    private List<String> frameworks;
    private List<String> libraries;
    private List<String> companies;
    private List<RoadmapStepDTO> roadmap;
    private List<CommunityLinkDTO> communityLinks;
    private List<ProjectDTO> projects;
}

