package com.nebula.Nebula.userdashboard.controller;

import com.nebula.Nebula.course.dto.CourseDto;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.userdashboard.service.PurchasedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/purchased-courses")
public class PurchasedCourseController {

    @Autowired
    private PurchasedCourseService purchasedCourseService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CourseDto>> getUserPurchasedCourses(@PathVariable UUID userId) {
        List<CourseDto> CourseDto = purchasedCourseService.getCoursesPurchasedByUser(userId);
        return ResponseEntity.ok(CourseDto);
    }
}
