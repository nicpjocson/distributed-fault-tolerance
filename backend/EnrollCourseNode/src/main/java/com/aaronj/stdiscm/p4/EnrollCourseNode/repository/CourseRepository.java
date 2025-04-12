package com.aaronj.stdiscm.p4.EnrollCourseNode.repository;

import com.aaronj.stdiscm.p4.EnrollCourseNode.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    Optional<Course> findByCode(String code); // Custom query method to find by code
}