package com.aaronj.stdiscm.p4.ViewGradesNode.controller;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.aaronj.stdiscm.p4.ViewGradesNode.model.Grade;
import com.aaronj.stdiscm.p4.ViewGradesNode.repository.GradeRepository;

import java.util.List;

@RestController
public class GradeController {

    private final GradeRepository gradeRepository;

    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/grades")
    public ResponseEntity<List<Grade>> getGrades(Authentication authentication) {
        // Extract the Jwt object from the Authentication principal
        Jwt jwt = (Jwt) authentication.getPrincipal();

        // Extract the 'sub' claim from the JWT
        String userId = jwt.getSubject(); // 'sub' claim is mapped to the subject

        // Query the repository for grades matching the userId
        List<Grade> grades = gradeRepository.findByIdUsername(userId);

        return ResponseEntity.ok(grades);
    }
}