package com.aaronj.stdiscm.p4.ViewGradesNode.repository;

import com.aaronj.stdiscm.p4.ViewGradesNode.model.Grade;
import com.aaronj.stdiscm.p4.ViewGradesNode.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    List<Grade> findByIdUsername(String username);
}