package com.aaronj.stdiscm.p4.EnrollCourseNode.controller;

import com.aaronj.stdiscm.p4.EnrollCourseNode.model.Enrollment;
import com.aaronj.stdiscm.p4.EnrollCourseNode.repository.EnrollmentRepository;
import com.aaronj.stdiscm.p4.EnrollCourseNode.repository.CourseRepository;
import com.aaronj.stdiscm.p4.EnrollCourseNode.model.Course;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/enrollments")
    public ResponseEntity<List<Enrollment>> getEnrollments(@AuthenticationPrincipal Jwt jwt) {
        // Extract username from the "sub" claim of the JWT
        String username = jwt.getClaim("sub");

        // Find enrollments by username
        List<Enrollment> enrollments = enrollmentRepository.findByUsername(username);

        return ResponseEntity.ok(enrollments);
    }

    @PostMapping("/enrollments")
    public ResponseEntity<?> createEnrollment(@RequestBody Enrollment enrollment, @AuthenticationPrincipal Jwt jwt) {
        // Extract username from the "sub" claim of the JWT
        String username = jwt.getClaim("sub");
        enrollment.setUsername(username);

        // Find the course by its code
        Course course = courseRepository.findByCode(enrollment.getCode()).orElse(null);
        if (course == null || !"OPEN".equalsIgnoreCase(course.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Enrollment failed: Course is not open for enrollment.");
        }

        // Save the enrollment
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }
}
