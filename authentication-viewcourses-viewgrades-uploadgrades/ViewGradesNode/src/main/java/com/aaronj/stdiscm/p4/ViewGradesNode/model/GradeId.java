package com.aaronj.stdiscm.p4.ViewGradesNode.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GradeId implements Serializable {
    private String username;
    private String code;

    // Default constructor
    public GradeId() {}

    // Constructor
    public GradeId(String username, String code) {
        this.username = username;
        this.code = code;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return Objects.equals(username, gradeId.username) && Objects.equals(code, gradeId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, code);
    }
}