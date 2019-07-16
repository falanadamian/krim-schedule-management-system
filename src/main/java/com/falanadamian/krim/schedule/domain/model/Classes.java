package com.falanadamian.krim.schedule.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "lecture", nullable = false)
    private Integer lecture;

    @NotNull
    @Column(name = "laboratory", nullable = false)
    private Integer laboratory;

    @NotNull
    @Column(name = "blackboard", nullable = false)
    private Integer blackboard;

    @NotNull
    @Column(name = "project", nullable = false)
    private Integer project;

    @NotNull
    @Column(name = "seminar", nullable = false)
    private Integer seminar;

    @Column(name = "practical")
    private Integer practical;

    @Column(name = "outdoor")
    private Integer outdoor;

    @Column(name = "workshop")
    private Integer workshop;

    @NotNull
    @Column(name = "elearning", nullable = false)
    private Integer elearning;

    @NotNull
    @Column(name = "other", nullable = false)
    private Integer other;

    @Column(name = "sum")
    private Integer sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLecture() {
        return lecture;
    }

    public Classes lecture(Integer lecture) {
        this.lecture = lecture;
        return this;
    }

    public void setLecture(Integer lecture) {
        this.lecture = lecture;
    }

    public Integer getLaboratory() {
        return laboratory;
    }

    public Classes laboratory(Integer laboratory) {
        this.laboratory = laboratory;
        return this;
    }

    public void setLaboratory(Integer laboratory) {
        this.laboratory = laboratory;
    }

    public Integer getBlackboard() {
        return blackboard;
    }

    public Classes blackboard(Integer blackboard) {
        this.blackboard = blackboard;
        return this;
    }

    public void setBlackboard(Integer blackboard) {
        this.blackboard = blackboard;
    }

    public Integer getProject() {
        return project;
    }

    public Classes project(Integer project) {
        this.project = project;
        return this;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getSeminar() {
        return seminar;
    }

    public Classes seminar(Integer seminar) {
        this.seminar = seminar;
        return this;
    }

    public void setSeminar(Integer seminar) {
        this.seminar = seminar;
    }

    public Integer getPractical() {
        return practical;
    }

    public Classes practical(Integer practical) {
        this.practical = practical;
        return this;
    }

    public void setPractical(Integer practical) {
        this.practical = practical;
    }

    public Integer getOutdoor() {
        return outdoor;
    }

    public Classes outdoor(Integer outdoor) {
        this.outdoor = outdoor;
        return this;
    }

    public void setOutdoor(Integer outdoor) {
        this.outdoor = outdoor;
    }

    public Integer getWorkshop() {
        return workshop;
    }

    public Classes workshop(Integer workshop) {
        this.workshop = workshop;
        return this;
    }

    public void setWorkshop(Integer workshop) {
        this.workshop = workshop;
    }

    public Integer getElearning() {
        return elearning;
    }

    public Classes elearning(Integer elearning) {
        this.elearning = elearning;
        return this;
    }

    public void setElearning(Integer elearning) {
        this.elearning = elearning;
    }

    public Integer getOther() {
        return other;
    }

    public Classes other(Integer other) {
        this.other = other;
        return this;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getSum() {
        return sum;
    }

    public Classes sum(Integer sum) {
        this.sum = sum;
        return this;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return Objects.equals(id, classes.id) &&
            Objects.equals(lecture, classes.lecture) &&
            Objects.equals(laboratory, classes.laboratory) &&
            Objects.equals(blackboard, classes.blackboard) &&
            Objects.equals(project, classes.project) &&
            Objects.equals(seminar, classes.seminar) &&
            Objects.equals(practical, classes.practical) &&
            Objects.equals(outdoor, classes.outdoor) &&
            Objects.equals(workshop, classes.workshop) &&
            Objects.equals(elearning, classes.elearning) &&
            Objects.equals(other, classes.other) &&
            Objects.equals(sum, classes.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lecture, laboratory, blackboard, project, seminar, practical, outdoor, workshop, elearning, other, sum);
    }

    @Override
    public String toString() {
        return "Classes{" +
            "id=" + id +
            ", lecture=" + lecture +
            ", laboratory=" + laboratory +
            ", blackboard=" + blackboard +
            ", project=" + project +
            ", seminar=" + seminar +
            ", practical=" + practical +
            ", outdoor=" + outdoor +
            ", workshop=" + workshop +
            ", elearning=" + elearning +
            ", other=" + other +
            ", sum=" + sum +
            '}';
    }
}
