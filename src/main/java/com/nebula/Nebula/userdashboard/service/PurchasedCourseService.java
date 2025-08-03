package com.nebula.Nebula.userdashboard.service;

import com.nebula.Nebula.auth.entity.LearnerUser;
import com.nebula.Nebula.auth.repo.LearnerUserRepo;
import com.nebula.Nebula.course.dto.CourseDto;
import com.nebula.Nebula.course.mapper.CourseMapper;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.userdashboard.model.PurchasedCourse;
import com.nebula.Nebula.userdashboard.repo.PurchasedCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchasedCourseService {

    @Autowired
    private  PurchasedCourseRepo purchasedCourseRepo;

    @Autowired
    private  LearnerUserRepo learnerUserRepo;

    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDto> getCoursesPurchasedByUser(UUID userId) {
        LearnerUser user = learnerUserRepo.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        List<PurchasedCourse> purchasedCourses = purchasedCourseRepo.findByUser(user);

        return purchasedCourses.stream()
                .map(p -> courseMapper.toCourseDto(p.getCourse()))
                .collect(Collectors.toList());
    }
}
