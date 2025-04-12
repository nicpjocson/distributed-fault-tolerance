package com.stdiscm.problemset4.ViewAvailableCoursesNode.repository;

import com.stdiscm.problemset4.ViewAvailableCoursesNode.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
