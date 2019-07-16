package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.Language;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyLevel;
import com.falanadamian.krim.schedule.domain.model.enumeration.StudyType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "module_general_information")
public class ModuleGeneralInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "study_course", nullable = false)
    private String studyCourse;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "faculty", nullable = false)
    private String faculty;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "study_level", nullable = false)
    private StudyLevel studyLevel;

    @Column(name = "specialty")
    private String specialty;

    @NotNull
    @Column(name = "study_field", nullable = false)
    private String studyField;

    @NotNull
    @Column(name = "semester", nullable = false)
    private Integer semester;

    @NotNull
    @Column(name = "education_profile", nullable = false)
    private String educationProfile;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_language", nullable = false)
    private Language lectureLanguage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "study_type", nullable = false)
    private StudyType studyType;

    @OneToOne(mappedBy = "moduleGeneralInformation", cascade = CascadeType.ALL)
    @JsonIgnore
    private Module module;

    @ManyToOne()
    private UserSignature responsibleUserSignature;

    @ManyToMany(mappedBy = "modulesInManagement")
    private List<UserSignature> academicUserSignatures;

    @ManyToOne
    @JsonIgnoreProperties("modulesInCharge")
    private User responsibleTeacher;

    @ManyToMany(mappedBy = "modulesInManagement")
    @JsonIgnore
    private Set<User> academicTeachers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ModuleGeneralInformation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyCourse() {
        return studyCourse;
    }

    public ModuleGeneralInformation studyCourse(String studyCourse) {
        this.studyCourse = studyCourse;
        return this;
    }

    public void setStudyCourse(String studyCourse) {
        this.studyCourse = studyCourse;
    }

    public String getCode() {
        return code;
    }

    public ModuleGeneralInformation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFaculty() {
        return faculty;
    }

    public ModuleGeneralInformation faculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public ModuleGeneralInformation studyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
        return this;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        this.studyLevel = studyLevel;
    }

    public String getSpecialty() {
        return specialty;
    }

    public ModuleGeneralInformation specialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getStudyField() {
        return studyField;
    }

    public ModuleGeneralInformation studyField(String studyField) {
        this.studyField = studyField;
        return this;
    }

    public void setStudyField(String studyField) {
        this.studyField = studyField;
    }

    public Integer getSemester() {
        return semester;
    }

    public ModuleGeneralInformation semester(Integer semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getEducationProfile() {
        return educationProfile;
    }

    public ModuleGeneralInformation educationProfile(String educationProfile) {
        this.educationProfile = educationProfile;
        return this;
    }

    public void setEducationProfile(String educationProfile) {
        this.educationProfile = educationProfile;
    }

    public Language getLectureLanguage() {
        return lectureLanguage;
    }

    public ModuleGeneralInformation lectureLanguage(Language lectureLanguage) {
        this.lectureLanguage = lectureLanguage;
        return this;
    }

    public void setLectureLanguage(Language lectureLanguage) {
        this.lectureLanguage = lectureLanguage;
    }

    public StudyType getStudyType() {
        return studyType;
    }

    public ModuleGeneralInformation studyType(StudyType studyType) {
        this.studyType = studyType;
        return this;
    }

    public void setStudyType(StudyType studyType) {
        this.studyType = studyType;
    }

    public UserSignature getResponsibleUserSignature() {
        return responsibleUserSignature;
    }

    public ModuleGeneralInformation responsibleUserSignature(UserSignature responsibleUserSignature) {
        this.responsibleUserSignature = responsibleUserSignature;
        return this;
    }

    public void setResponsibleUserSignature(UserSignature responsibleUserSignature) {
        this.responsibleUserSignature = responsibleUserSignature;
    }

    public List<UserSignature> getAcademicUserSignatures() {
        return academicUserSignatures;
    }

    public ModuleGeneralInformation academicUserSignatures(List<UserSignature> academicUserSignatures) {
        this.academicUserSignatures = academicUserSignatures;
        return this;
    }

    public void setAcademicUserSignatures(List<UserSignature> academicUserSignatures) {
        this.academicUserSignatures = academicUserSignatures;
    }

    public Module getModule() {
        return module;
    }

    public ModuleGeneralInformation module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public User getResponsibleTeacher() {
        return responsibleTeacher;
    }

    public ModuleGeneralInformation responsibleTeacher(User myUser) {
        this.responsibleTeacher = myUser;
        return this;
    }

    public void setResponsibleTeacher(User myUser) {
        this.responsibleTeacher = myUser;
    }

    public Set<User> getAcademicTeachers() {
        return academicTeachers;
    }

    public ModuleGeneralInformation academicTeachers(Set<User> myUsers) {
        this.academicTeachers = myUsers;
        return this;
    }

    public ModuleGeneralInformation addAcademicTeachers(User myUser) {
        this.academicTeachers.add(myUser);
        myUser.getModulesInManagement().add(this);
        return this;
    }

    public ModuleGeneralInformation removeAcademicTeachers(User myUser) {
        this.academicTeachers.remove(myUser);
        myUser.getModulesInManagement().remove(this);
        return this;
    }

    public void setAcademicTeachers(Set<User> myUsers) {
        this.academicTeachers = myUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleGeneralInformation that = (ModuleGeneralInformation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(studyCourse, that.studyCourse) &&
                Objects.equals(code, that.code) &&
                Objects.equals(faculty, that.faculty) &&
                studyLevel == that.studyLevel &&
                Objects.equals(specialty, that.specialty) &&
                Objects.equals(studyField, that.studyField) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(educationProfile, that.educationProfile) &&
                lectureLanguage == that.lectureLanguage &&
                studyType == that.studyType &&
                Objects.equals(module, that.module) &&
                Objects.equals(responsibleUserSignature, that.responsibleUserSignature) &&
                Objects.equals(academicUserSignatures, that.academicUserSignatures) &&
                Objects.equals(responsibleTeacher, that.responsibleTeacher) &&
                Objects.equals(academicTeachers, that.academicTeachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, studyCourse, code, faculty, studyLevel, specialty, studyField, semester, educationProfile, lectureLanguage, studyType);
    }

    @Override
    public String toString() {
        return "ModuleGeneralInformation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studyCourse='" + studyCourse + '\'' +
                ", code='" + code + '\'' +
                ", faculty='" + faculty + '\'' +
                ", studyLevel=" + studyLevel +
                ", specialty='" + specialty + '\'' +
                ", studyField='" + studyField + '\'' +
                ", semester=" + semester +
                ", educationProfile='" + educationProfile + '\'' +
                ", lectureLanguage=" + lectureLanguage +
                ", studyType=" + studyType +
                '}';
    }
}
