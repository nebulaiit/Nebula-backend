package com.nebula.Nebula.course.controller;

import com.nebula.Nebula.auth.dto.ResponseBodyDto;
import com.nebula.Nebula.course.dto.CourseContentDto;
import com.nebula.Nebula.course.dto.CourseDto;
import com.nebula.Nebula.course.model.Course;
import com.nebula.Nebula.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses(){

        List<CourseDto> courses = courseService.getAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<ResponseBodyDto> createCourse(@RequestBody Course course){

        ResponseBodyDto responseBodyDto = courseService.createCourse(course);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.CREATED);

    }


    @GetMapping("/courses/language/{language}")
    public ResponseEntity<List<CourseDto>> getAllCoursesByLanguage(@PathVariable String language){

        List<CourseDto> courses = courseService.getAllCoursesByLanguage(language);

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id){

        Course course = courseService.getCourseById(id);

        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @GetMapping("/courses/content/{id}")
    public ResponseEntity<CourseContentDto> getCourseContentByID(@PathVariable UUID id){

      CourseContentDto courseContentDto = courseService.getCourseContentByID(id);

        return new ResponseEntity<>(courseContentDto, HttpStatus.OK);

    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ResponseBodyDto> updateCourse(@PathVariable UUID id, @RequestBody Course updatedCourse) {

        ResponseBodyDto responseBodyDto = courseService.updateCourse(id, updatedCourse);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);
    }



    @DeleteMapping("/course/{id}")
    public ResponseEntity<ResponseBodyDto> deleteCourseById(@PathVariable UUID id){

        ResponseBodyDto responseBodyDto = courseService.deleteCourseById(id);

        return new ResponseEntity<>(responseBodyDto, HttpStatus.OK);

    }

}
