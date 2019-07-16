package com.falanadamian.krim.schedule.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "class_limit")
public class ClassLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "laboratory_class_limit")
    private Integer laboratoryClassLimit;

    @Column(name = "blackboard_class_limit")
    private Integer blackboardClassLimit;

    @Column(name = "project_class_limit")
    private Integer projectClassLimit;

    @Column(name = "seminar_class_limit")
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

    public ClassLimit laboratoryClassLimit(Integer laboratoryClassLimit) {
        this.laboratoryClassLimit = laboratoryClassLimit;
        return this;
    }

    public void setLaboratoryClassLimit(Integer laboratoryClassLimit) {
        this.laboratoryClassLimit = laboratoryClassLimit;
    }

    public Integer getBlackboardClassLimit() {
        return blackboardClassLimit;
    }

    public ClassLimit blackboardClassLimit(Integer blackboardClassLimit) {
        this.blackboardClassLimit = blackboardClassLimit;
        return this;
    }

    public void setBlackboardClassLimit(Integer blackboardClassLimit) {
        this.blackboardClassLimit = blackboardClassLimit;
    }

    public Integer getProjectClassLimit() {
        return projectClassLimit;
    }

    public ClassLimit projectClassLimit(Integer projectClassLimit) {
        this.projectClassLimit = projectClassLimit;
        return this;
    }

    public void setProjectClassLimit(Integer projectClassLimit) {
        this.projectClassLimit = projectClassLimit;
    }

    public Integer getSeminarClassLimit() {
        return seminarClassLimit;
    }

    public ClassLimit seminarClassLimit(Integer seminarClassLimit) {
        this.seminarClassLimit = seminarClassLimit;
        return this;
    }

    public void setSeminarClassLimit(Integer seminarClassLimit) {
        this.seminarClassLimit = seminarClassLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassLimit that = (ClassLimit) o;
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
        return "ClassLimit{" +
            "id=" + id +
            ", laboratoryClassLimit=" + laboratoryClassLimit +
            ", blackboardClassLimit=" + blackboardClassLimit +
            ", projectClassLimit=" + projectClassLimit +
            ", seminarClassLimit=" + seminarClassLimit +
            '}';
    }
}
