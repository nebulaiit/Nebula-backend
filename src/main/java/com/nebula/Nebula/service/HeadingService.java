package com.nebula.Nebula.service;

import com.nebula.Nebula.model.Heading;
import com.nebula.Nebula.repository.HeadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadingService {

    @Autowired
    private HeadingRepo headingRepo;


    public List<Heading> getAllHeading() {
        return headingRepo.findAll();
    }
}
