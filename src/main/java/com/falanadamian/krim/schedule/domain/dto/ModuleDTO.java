package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.enumeration.ExamType;
import com.falanadamian.krim.schedule.domain.model.enumeration.ModuleType;
import com.falanadamian.krim.schedule.domain.model.enumeration.Semester;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


public class ModuleDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;

    @NotNull
    private ModuleType type;

    @NotNull
    private Semester semester;

    @NotNull
    private ExamType examType;

    private Long userId;

    private ClassesDTO classes;

//    private ModuleGeneralInformationDTO moduleGeneralInformation;

    private Long moduleGeneralInformationId;

    private ClassLimitDTO classLimit;

    private Set<Long> errandIds;

    private Set<Long> scheduleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModuleType getType() {
        return type;
    }

    public void setType(ModuleType type) {
        this.type = type;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public ClassesDTO getClasses() {
        return classes;
    }

    public void setClasses(ClassesDTO classes) {
        this.classes = classes;
    }

    public ClassLimitDTO getClassLimit() {
        return classLimit;
    }

    public void setClassLimit(ClassLimitDTO classLimit) {
        this.classLimit = classLimit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long myUserId) {
        this.userId = myUserId;
    }

/*    public ModuleGeneralInformationDTO getModuleGeneralInformation() {
        return moduleGeneralInformation;
    }

    public void setModuleGeneralInformation(ModuleGeneralInformationDTO moduleGeneralInformation) {
        this.moduleGeneralInformation = moduleGeneralInformation;
    }*/

    public Set<Long> getErrandIds() {
        return errandIds;
    }

    public void setErrandIds(Set<Long> errandIds) {
        this.errandIds = errandIds;
    }

    public Long getModuleGeneralInformationId() {
        return moduleGeneralInformationId;
    }

    public void setModuleGeneralInformationId(Long moduleGeneralInformationId) {
        this.moduleGeneralInformationId = moduleGeneralInformationId;
    }

    public Set<Long> getScheduleIds() {
        return scheduleIds;
    }

    public void setScheduleIds(Set<Long> scheduleIds) {
        this.scheduleIds = scheduleIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleDTO moduleDTO = (ModuleDTO) o;
        return Objects.equals(id, moduleDTO.id) &&
                Objects.equals(code, moduleDTO.code) &&
                Objects.equals(name, moduleDTO.name) &&
                type == moduleDTO.type &&
                semester == moduleDTO.semester &&
                examType == moduleDTO.examType &&
                Objects.equals(userId, moduleDTO.userId) &&
                Objects.equals(classes, moduleDTO.classes) &&
//                Objects.equals(moduleGeneralInformation, moduleDTO.moduleGeneralInformation) &&
                Objects.equals(moduleGeneralInformationId, moduleDTO.moduleGeneralInformationId) &&
                Objects.equals(classLimit, moduleDTO.classLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, type, semester, examType, userId, classes, moduleGeneralInformationId, classLimit);
    }

    @Override
    public String toString() {
        return "ModuleDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", semester=" + semester +
                ", examType=" + examType +
                ", userId=" + userId +
                ", classes=" + classes +
                ", moduleGeneralInformationId=" + moduleGeneralInformationId +
                ", classLimit=" + classLimit +
                '}';
    }
}
