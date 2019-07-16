package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.Classes;
import com.falanadamian.krim.schedule.domain.model.enumeration.ExamType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ScheduleDTO implements Serializable {

    private Long id;

    private ExamType examType;

    private Long assigneeId;

    private Long assignorId;

    private Classes classes;

//    private Set<ErrandDTO> errands = new HashSet<>();

//    private ErrandDTO errand;

    private Long moduleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long myUserId) {
        this.assigneeId = myUserId;
    }

    public Long getAssignorId() {
        return assignorId;
    }

    public void setAssignorId(Long myUserId) {
        this.assignorId = myUserId;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    /*public Set<ErrandDTO> getErrands() {
        return errands;
    }

    public void setErrands(Set<ErrandDTO> errands) {
        this.errands = errands;
    }*/

    /*public ErrandDTO getErrand() {
        return errand;
    }

    public void setErrand(ErrandDTO errand) {
        this.errand = errand;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDTO that = (ScheduleDTO) o;
        return Objects.equals(id, that.id) &&
                examType == that.examType &&
                Objects.equals(assigneeId, that.assigneeId) &&
                Objects.equals(assignorId, that.assignorId) &&
                Objects.equals(classes, that.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examType, assigneeId, assignorId, classes);
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "id=" + id +
                ", examType=" + examType +
                ", assigneeId=" + assigneeId +
                ", assignorId=" + assignorId +
                ", classes=" + classes +
                '}';
    }
}
