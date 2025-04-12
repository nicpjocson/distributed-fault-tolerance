package com.aaronj.stdiscm.p4.EnrollCourseNode.repository;

import com.aaronj.stdiscm.p4.EnrollCourseNode.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    List<Enrollment> findByUsername(String username); // Custom query method to find enrollments by username
}
