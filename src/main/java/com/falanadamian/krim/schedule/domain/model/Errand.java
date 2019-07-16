package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.ClassType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "errand")
public class Errand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "class_type", nullable = false)
    private ClassType classType;

    @NotNull
    @Column(name = "hours", nullable = false)
    private Integer hours;

    @NotNull
    @Column(name = "group_number", nullable = false)
    private Integer groups;

    @NotNull
    @Column(name = "students", nullable = false)
    private Integer students;

    @NotNull
    @Column(name = "additional_hours_ratio", nullable = false)
    private Integer additionalHoursRatio;

    @NotNull
    @Column(name = "errand_number", nullable = false)
    private String errandNumber;

    @NotNull
    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "note")
    private String note;

    @ManyToOne(optional = true)
    private Module module;

    /*@ManyToOne
    @JsonIgnoreProperties("codes")
    private Schedule schedule;*/

    /*@OneToMany(mappedBy = "errand", fetch = FetchType.EAGER)
    private Set<Schedule> schedules = new HashSet<>();*/

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

    public ClassType getClassType() {
        return classType;
    }

    public Errand classType(ClassType classType) {
        this.classType = classType;
        return this;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public Integer getHours() {
        return hours;
    }

    public Errand hours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getGroups() {
        return groups;
    }

    public Errand groups(Integer groups) {
        this.groups = groups;
        return this;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getStudents() {
        return students;
    }

    public Errand students(Integer students) {
        this.students = students;
        return this;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public Integer getAdditionalHoursRatio() {
        return additionalHoursRatio;
    }

    public Errand additionalHoursRatio(Integer additionalHoursRatio) {
        this.additionalHoursRatio = additionalHoursRatio;
        return this;
    }

    public void setAdditionalHoursRatio(Integer additionalHoursRatio) {
        this.additionalHoursRatio = additionalHoursRatio;
    }

    public String getErrandNumber() {
        return errandNumber;
    }

    public Errand errandNumber(String errandNumber) {
        this.errandNumber = errandNumber;
        return this;
    }

    public void setErrandNumber(String errandNumber) {
        this.errandNumber = errandNumber;
    }

    public String getDepartment() {
        return department;
    }

    public Errand department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNote() {
        return note;
    }

    public Errand note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Module getModule() {
        return module;
    }

    public Errand module(Module module) {
        this.module = module;
        return this;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    /*public Schedule getSchedule() {
        return schedule;
    }

    public Errand schedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }*/

    /*public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Errand errand = (Errand) o;
        return Objects.equals(id, errand.id) &&
                classType == errand.classType &&
                Objects.equals(hours, errand.hours) &&
                Objects.equals(groups, errand.groups) &&
                Objects.equals(students, errand.students) &&
                Objects.equals(additionalHoursRatio, errand.additionalHoursRatio) &&
                Objects.equals(errandNumber, errand.errandNumber) &&
                Objects.equals(department, errand.department) &&
                Objects.equals(note, errand.note) &&
                Objects.equals(module, errand.module);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classType, hours, groups, students, additionalHoursRatio, errandNumber, department, note, module);
    }

    @Override
    public String toString() {
        return "Errand{" +
                "id=" + id +
                ", classType=" + classType +
                ", hours=" + hours +
                ", groups=" + groups +
                ", students=" + students +
                ", additionalHoursRatio=" + additionalHoursRatio +
                ", errandNumber='" + errandNumber + '\'' +
                ", department='" + department + '\'' +
                ", note='" + note + '\'' +
                ", module=" + module +
                '}';
    }
}
