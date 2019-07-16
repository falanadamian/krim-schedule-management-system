package com.falanadamian.krim.schedule.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class ClassLimitDTO implements Serializable {

    private Long id;

    private Integer laboratoryClassLimit;

    private Integer blackboardClassLimit;

    private Integer projectClassLimit;

    private Integer seminarClassLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLaboratoryClassLimit() {
        return laboratoryClassLimit;
    }

    public void setLaboratoryClassLimit(Integer laboratoryClassLimit) {
        this.laboratoryClassLimit = laboratoryClassLimit;
    }

    public Integer getBlackboardClassLimit() {
        return blackboardClassLimit;
    }

    public void setBlackboardClassLimit(Integer blackboardClassLimit) {
        this.blackboardClassLimit = blackboardClassLimit;
    }

    public Integer getProjectClassLimit() {
        return projectClassLimit;
    }

    public void setProjectClassLimit(Integer projectClassLimit) {
        this.projectClassLimit = projectClassLimit;
    }

    public Integer getSeminarClassLimit() {
        return seminarClassLimit;
    }

    public void setSeminarClassLimit(Integer seminarClassLimit) {
        this.seminarClassLimit = seminarClassLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassLimitDTO that = (ClassLimitDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(laboratoryClassLimit, that.laboratoryClassLimit) &&
            Objects.equals(blackboardClassLimit, that.blackboardClassLimit) &&
            Objects.equals(projectClassLimit, that.projectClassLimit) &&
            Objects.equals(seminarClassLimit, that.seminarClassLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, laboratoryClassLimit, blackboardClassLimit, projectClassLimit, seminarClassLimit);
    }

    @Override
    public String toString() {
        return "ClassLimitDTO{" +
            "id=" + id +
            ", laboratoryClassLimit=" + laboratoryClassLimit +
            ", blackboardClassLimit=" + blackboardClassLimit +
            ", projectClassLimit=" + projectClassLimit +
            ", seminarClassLimit=" + seminarClassLimit +
            '}';
    }
}
