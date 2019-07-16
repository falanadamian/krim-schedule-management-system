package com.falanadamian.krim.schedule.domain.dto;
import com.falanadamian.krim.schedule.domain.model.enumeration.ClassType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ErrandDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private ClassType classType;

    @NotNull
    private Integer hours;

    @NotNull
    private Integer groups;

    @NotNull
    private Integer students;

    @NotNull
    private Integer additionalHoursRatio;

    @NotNull
    private String errandNumber;

    @NotNull
    private String department;

    private String note;

    private Long moduleId;

    private Long documentId;

//    private Long scheduleId;

//    private Set<Long> schedulesIds = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getStudents() {
        return students;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public Integer getAdditionalHoursRatio() {
        return additionalHoursRatio;
    }

    public void setAdditionalHoursRatio(Integer additionalHoursRatio) {
        this.additionalHoursRatio = additionalHoursRatio;
    }

    public String getErrandNumber() {
        return errandNumber;
    }

    public void setErrandNumber(String errandNumber) {
        this.errandNumber = errandNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /*public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }*/


    /*public Set<Long> getSchedulesIds() {
        return schedulesIds;
    }

    public void setSchedulesIds(Set<Long> schedulesIds) {
        this.schedulesIds = schedulesIds;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrandDTO errandDTO = (ErrandDTO) o;
        return Objects.equals(id, errandDTO.id) &&
                classType == errandDTO.classType &&
                Objects.equals(hours, errandDTO.hours) &&
                Objects.equals(groups, errandDTO.groups) &&
                Objects.equals(students, errandDTO.students) &&
                Objects.equals(additionalHoursRatio, errandDTO.additionalHoursRatio) &&
                Objects.equals(errandNumber, errandDTO.errandNumber) &&
                Objects.equals(department, errandDTO.department) &&
                Objects.equals(note, errandDTO.note) &&
                Objects.equals(moduleId, errandDTO.moduleId) &&
                Objects.equals(documentId, errandDTO.documentId) &&
                Objects.equals(code, errandDTO.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classType, hours, groups, students, additionalHoursRatio, errandNumber, department, note, moduleId, documentId, code);
    }

    @Override
    public String toString() {
        return "ErrandDTO{" +
                "id=" + id +
                ", classType=" + classType +
                ", hours=" + hours +
                ", groups=" + groups +
                ", students=" + students +
                ", additionalHoursRatio=" + additionalHoursRatio +
                ", errandNumber='" + errandNumber + '\'' +
                ", department='" + department + '\'' +
                ", note='" + note + '\'' +
                ", moduleId=" + moduleId +
                ", documentId=" + documentId +
                ", code='" + code + '\'' +
                '}';
    }
}
