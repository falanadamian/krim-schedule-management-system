package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.enumeration.Language;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyLevel;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ModuleGeneralInformationDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String studyCourse;

    @NotNull
    private String code;

    @NotNull
    private String faculty;

    @NotNull
    private StudyLevel studyLevel;

    @NotNull
    private String studyField;

    @NotNull
    private Integer semester;

    @NotNull
    private String educationProfile;

    @NotNull
    private Language lectureLanguage;

    @NotNull
    private StudyType studyType;

    private Long moduleId;

    private Long responsibleUserSignatureId;

    private List<Long> academicUserSignaturesIds;

    private Long responsibleTeacherId;

    private List<Long> academicTeachersIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyCourse() {
        return studyCourse;
    }

    public void setStudyCourse(String studyCourse) {
        this.studyCourse = studyCourse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getStudyField() {
        return studyField;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getEducationProfile() {
        return educationProfile;
    }

    public void setEducationProfile(String educationProfile) {
        this.educationProfile = educationProfile;
    }

    public Language getLectureLanguage() {
        return lectureLanguage;
    }

    public void setLectureLanguage(Language lectureLanguage) {
        this.lectureLanguage = lectureLanguage;
    }

    public StudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(StudyType studyType) {
        this.studyType = studyType;
    }

    public Long getResponsibleTeacherId() {
        return responsibleTeacherId;
    }

    public void setResponsibleTeacherId(Long myUserId) {
        this.responsibleTeacherId = myUserId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getResponsibleUserSignatureId() {
        return responsibleUserSignatureId;
    }

    public void setResponsibleUserSignatureId(Long responsibleUserSignatureId) {
        this.responsibleUserSignatureId = responsibleUserSignatureId;
    }

    public List<Long> getAcademicUserSignaturesIds() {
        return academicUserSignaturesIds;
    }

    public void setAcademicUserSignaturesIds(List<Long> academicUserSignaturesIds) {
        this.academicUserSignaturesIds = academicUserSignaturesIds;
    }

    public List<Long> getAcademicTeachersIds() {
        return academicTeachersIds;
    }

    public void setAcademicTeachersIds(List<Long> academicTeachersIds) {
        this.academicTeachersIds = academicTeachersIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleGeneralInformationDTO that = (ModuleGeneralInformationDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(studyCourse, that.studyCourse) &&
                Objects.equals(code, that.code) &&
                Objects.equals(faculty, that.faculty) &&
                studyLevel == that.studyLevel &&
                Objects.equals(studyField, that.studyField) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(educationProfile, that.educationProfile) &&
                lectureLanguage == that.lectureLanguage &&
                studyType == that.studyType &&
                Objects.equals(moduleId, that.moduleId) &&
                Objects.equals(responsibleUserSignatureId, that.responsibleUserSignatureId) &&
                Objects.equals(academicUserSignaturesIds, that.academicUserSignaturesIds) &&
                Objects.equals(responsibleTeacherId, that.responsibleTeacherId) &&
                Objects.equals(academicTeachersIds, that.academicTeachersIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studyCourse, code, faculty, studyLevel, studyField, semester, educationProfile, lectureLanguage, studyType, moduleId, responsibleUserSignatureId, academicUserSignaturesIds, responsibleTeacherId, academicTeachersIds);
    }

    @Override
    public String toString() {
        return "ModuleGeneralInformationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studyCourse='" + studyCourse + '\'' +
                ", code='" + code + '\'' +
                ", faculty='" + faculty + '\'' +
                ", studyLevel=" + studyLevel +
                ", studyField='" + studyField + '\'' +
                ", semester=" + semester +
                ", educationProfile='" + educationProfile + '\'' +
                ", lectureLanguage=" + lectureLanguage +
                ", studyType=" + studyType +
                ", moduleId=" + moduleId +
                ", responsibleUserSignatureId=" + responsibleUserSignatureId +
                ", academicUserSignaturesIds=" + academicUserSignaturesIds +
                ", responsibleTeacherId=" + responsibleTeacherId +
                ", academicTeachersIds=" + academicTeachersIds +
                '}';
    }
}
