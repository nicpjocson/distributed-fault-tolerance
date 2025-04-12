package com.aaronj.stdiscm.p4.UploadGradesNode.repository;

import com.aaronj.stdiscm.p4.UploadGradesNode.model.Grade;
import com.aaronj.stdiscm.p4.UploadGradesNode.model.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
}