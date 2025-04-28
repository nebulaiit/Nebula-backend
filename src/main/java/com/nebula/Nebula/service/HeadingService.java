package com.nebula.Nebula.service;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.dto.HeadingDto;
import com.nebula.Nebula.dto.TutorialDto;
import com.nebula.Nebula.mapper.HeadingMapper;
import com.nebula.Nebula.mapper.TutorialMapper;
import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.model.Tutorial;
import com.nebula.Nebula.repository.HeadingRepo;
import com.nebula.Nebula.repository.TutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeadingService {


    @Autowired
    private HeadingRepo headingRepo;

    @Autowired
    private TutorialRepo tutorialRepo;

    @Autowired
    private HeadingMapper headingMapper;




    public List<HeadingDto> getAllHeading() {
        List<Heading> headings = headingRepo.findAll();

        return headings.stream().map(headingMapper::toDto).collect(Collectors.toList());
    }

    public ResponseBodyDto addHeading(Heading heading) {

        Heading heading1= headingRepo.findByHeadingName(heading.getHeadingName());

        if (heading1 != null){
            headingRepo.save(heading);
            return ResponseBodyDto.builder().code(201).message("Heading has been Created").build();
        }

        return ResponseBodyDto.builder().code(400).message("Error while adding Heading").build();

    }


}
