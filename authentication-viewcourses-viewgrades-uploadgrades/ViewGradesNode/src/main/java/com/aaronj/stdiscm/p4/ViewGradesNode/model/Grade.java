package com.aaronj.stdiscm.p4.ViewGradesNode.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "grades")
@Getter
@Setter
public class Grade {
    @EmbeddedId
    private GradeId id;

    private Float grade;

    // Getters and setters for the composite key
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

