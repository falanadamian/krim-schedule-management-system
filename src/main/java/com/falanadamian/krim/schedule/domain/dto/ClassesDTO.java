package com.falanadamian.krim.schedule.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class ClassesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer lecture;

    @NotNull
    private Integer laboratory;

    @NotNull
    private Integer blackboard;

    @NotNull
    private Integer project;

    @NotNull
    private Integer seminar;

    @NotNull
    private Integer practical;

    @NotNull
    private Integer outdoor;

    @NotNull
    private Integer workshop;

    @NotNull
    private Integer elearning;

    @NotNull
    private Integer other;

    @NotNull
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

    public void setLecture(Integer lecture) {
        this.lecture = lecture;
    }

    public Integer getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Integer laboratory) {
        this.laboratory = laboratory;
    }

    public Integer getBlackboard() {
        return blackboard;
    }

    public void setBlackboard(Integer blackboard) {
        this.blackboard = blackboard;
    }

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getSeminar() {
        return seminar;
    }

    public void setSeminar(Integer seminar) {
        this.seminar = seminar;
    }

    public Integer getPractical() {
        return practical;
    }

    public void setPractical(Integer practical) {
        this.practical = practical;
    }

    public Integer getOutdoor() {
        return outdoor;
    }

    public void setOutdoor(Integer outdoor) {
        this.outdoor = outdoor;
    }

    public Integer getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Integer workshop) {
        this.workshop = workshop;
    }

    public Integer getElearning() {
        return elearning;
    }

    public void setElearning(Integer elearning) {
        this.elearning = elearning;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassesDTO that = (ClassesDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(lecture, that.lecture) &&
            Objects.equals(laboratory, that.laboratory) &&
            Objects.equals(blackboard, that.blackboard) &&
            Objects.equals(project, that.project) &&
            Objects.equals(seminar, that.seminar) &&
            Objects.equals(practical, that.practical) &&
            Objects.equals(outdoor, that.outdoor) &&
            Objects.equals(workshop, that.workshop) &&
            Objects.equals(elearning, that.elearning) &&
            Objects.equals(other, that.other) &&
            Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lecture, laboratory, blackboard, project, seminar, practical, outdoor, workshop, elearning, other, sum);
    }

    @Override
    public String toString() {
        return "ClassesDTO{" +
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
