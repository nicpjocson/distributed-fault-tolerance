package com.aaronj.stdiscm.p4.UploadGradesNode.controller;

import com.aaronj.stdiscm.p4.UploadGradesNode.model.Grade;
import com.aaronj.stdiscm.p4.UploadGradesNode.repository.GradeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @PostMapping
    public ResponseEntity<Grade> addGrade(@RequestBody Grade grade) {
        Grade savedGrade = gradeRepository.save(grade);
        return ResponseEntity.ok(savedGrade);
    }
}