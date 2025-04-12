package com.aaronj.stdiscm.p4.UploadGradesNode.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {

    @EmbeddedId
    private GradeId id;

    private Float grade;

    // Getters and setters
    public GradeId getId() {
        return id;
    }

    public void setId(GradeId id) {
        this.id = id;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }
}

