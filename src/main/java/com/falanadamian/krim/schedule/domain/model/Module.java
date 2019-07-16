package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.ExamType;
import com.falanadamian.krim.schedule.domain.model.enumeration.ModuleType;
import com.falanadamian.krim.schedule.domain.model.enumeration.Semester;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "module")
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "ects")
    private Integer ECTS;

    @Column(name = "exam")
    private Boolean exam;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ModuleType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester")
    private Semester semester;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;

    @ManyToOne()
    @JoinColumn(unique = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private Classes classes;

    @OneToOne()
    @JoinColumn(unique = true)
    private ModuleGeneralInformation moduleGeneralInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private ClassLimit classLimit;

    @OneToMany(mappedBy = "module")
    private Set<Errand> errands = new HashSet<>();

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER)
    private Set<Schedule> schedules = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Module id(Long id) {
        this.id = id;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Module code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Module name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Module url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getECTS() {
        return ECTS;
    }

    public Module ECTS(Integer ECTS) {
        this.ECTS = ECTS;
        return this;
    }

    public void setECTS(Integer ECTS) {
        this.ECTS = ECTS;
    }

    public Boolean getExam() {
        return exam;
    }

    public Module exam(Boolean exam) {
        this.exam = exam;
        return this;
    }

    public void setExam(Boolean exam) {
        this.exam = exam;
    }

    public ModuleType getType() {
        return type;
    }

    public Module type(ModuleType type) {
        this.type = type;
        return this;
    }

    public void setType(ModuleType type) {
        this.type = type;
    }

    public Semester getSemester() {
        return semester;
    }

    public Module semester(Semester semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public ExamType getExamType() {
        return examType;
    }

    public Module examType(ExamType examType) {
        this.examType = examType;
        return this;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public User getUser() {
        return user;
    }

    public Module user(User myUser) {
        this.user = myUser;
        return this;
    }

    public void setUser(User myUser) {
        this.user = myUser;
    }

    public Classes getClasses() {
        return classes;
    }

    public Module classes(Classes classes) {
        this.classes = classes;
        return this;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public ModuleGeneralInformation getModuleGeneralInformation() {
        return moduleGeneralInformation;
    }

    public Module moduleGeneralInformation(ModuleGeneralInformation moduleGeneralInformation) {
        this.moduleGeneralInformation = moduleGeneralInformation;
        return this;
    }

    public void setModuleGeneralInformation(ModuleGeneralInformation moduleGeneralInformation) {
        this.moduleGeneralInformation = moduleGeneralInformation;
    }

    public ClassLimit getClassLimit() {
        return classLimit;
    }

    public Module classLimit(ClassLimit classLimit) {
        this.classLimit = classLimit;
        return this;
    }

    public void setClassLimit(ClassLimit classLimit) {
        this.classLimit = classLimit;
    }

    public Set<Errand> getErrands() {
        return errands;
    }

    public Module errands(Set<Errand> errands) {
        this.errands = errands;
        return this;
    }

    public void setErrands(Set<Errand> errands) {
        this.errands = errands;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(id, module.id) &&
            Objects.equals(code, module.code) &&
            Objects.equals(name, module.name) &&
            Objects.equals(url, module.url) &&
            Objects.equals(ECTS, module.ECTS) &&
            Objects.equals(exam, module.exam) &&
            type == module.type &&
            semester == module.semester &&
            examType == module.examType &&
            Objects.equals(user, module.user) &&
            Objects.equals(classes, module.classes) &&
            Objects.equals(moduleGeneralInformation, module.moduleGeneralInformation) &&
            Objects.equals(classLimit, module.classLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, url, ECTS, exam, type, semester, examType, classes, classLimit);
    }

    @Override
    public String toString() {
        return "Module{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", ECTS=" + ECTS +
            ", exam=" + exam +
            ", type=" + type +
            ", semester=" + semester +
            ", examType=" + examType +
            ", classes=" + classes +
            ", classLimit=" + classLimit +
            '}';
    }
}
