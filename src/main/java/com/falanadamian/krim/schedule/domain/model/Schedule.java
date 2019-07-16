package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.ExamType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type", nullable = false)
    private ExamType examType;

    @ManyToOne()
    @JoinColumn(name = "assignee")
    private User assignee;

    @ManyToOne()
    @JoinColumn(name = "assignor")
    private User assignor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true, name = "classes")
    private Classes classes;

    /*@OneToMany(mappedBy = "schedule", fetch = FetchType.EAGER)
    private Set<Errand> errands = new HashSet<>();*/

    /*@ManyToOne
    private Errand errand;*/

    @ManyToOne
    private Module module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExamType getExamType() {
        return examType;
    }

    public Schedule examType(ExamType examType) {
        this.examType = examType;
        return this;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public User getAssignee() {
        return assignee;
    }

    public Schedule assignee(User myUser) {
        this.assignee = myUser;
        return this;
    }

    public void setAssignee(User myUser) {
        this.assignee = myUser;
    }

    public User getAssignor() {
        return assignor;
    }

    public Schedule assignor(User myUser) {
        this.assignor = myUser;
        return this;
    }

    public void setAssignor(User myUser) {
        this.assignor = myUser;
    }

    public Classes getClasses() {
        return classes;
    }

    public Schedule classes(Classes classes) {
        this.classes = classes;
        return this;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    /*public Set<Errand> getErrands() {
        return errands;
    }

    public void setErrands(Set<Errand> errands) {
        this.errands = errands;
    }

    public Schedule addErrand(Errand errand) {
        this.errands.add(errand);
        errand.setSchedule(this);
        return this;
    }

    public Schedule removeErrand(Errand errand) {
        this.errands.remove(errand);
        errand.setSchedule(null);
        return this;
    }

    public void setCodes(Set<Errand> errands) {
        this.errands = errands;
    }*/

    /*public Errand getErrand() {
        return errand;
    }

    public void setErrand(Errand errand) {
        this.errand = errand;
    }*/

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) &&
                examType == schedule.examType &&
                Objects.equals(assignee, schedule.assignee) &&
                Objects.equals(assignor, schedule.assignor) &&
                Objects.equals(classes, schedule.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, examType, assignee, assignor, classes);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", examType=" + examType +
                ", assignee=" + assignee +
                ", assignor=" + assignor +
                ", classes=" + classes +
                '}';
    }
}
