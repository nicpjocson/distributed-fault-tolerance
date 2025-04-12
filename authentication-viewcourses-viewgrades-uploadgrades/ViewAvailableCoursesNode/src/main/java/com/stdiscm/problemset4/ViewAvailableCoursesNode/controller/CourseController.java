package com.stdiscm.problemset4.ViewAvailableCoursesNode.controller;

import com.stdiscm.problemset4.ViewAvailableCoursesNode.model.Course;
import com.stdiscm.problemset4.ViewAvailableCoursesNode.repository.CourseRepository; // Ensure this matches the actual package of ProductRepository

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getProducts() {
        return ResponseEntity.ok(courseRepository.findAll());
    }
}
