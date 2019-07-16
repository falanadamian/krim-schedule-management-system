package com.falanadamian.krim.schedule.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "study_info")
public class StudyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study_year")
    private Integer studyYear;

    @Column(name = "registered_phd")
    private Boolean registeredPhD;

    @Column(name = "scholarship")
    private Boolean scholarship;

    @OneToOne
    @JoinColumn(unique = true)
    private User patron;

    @OneToOne(mappedBy = "studyInfo")
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public StudyInfo studyYear(Integer studyYear) {
        this.studyYear = studyYear;
        return this;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public Boolean isRegisteredPhD() {
        return registeredPhD;
    }

    public StudyInfo registeredPhD(Boolean registeredPhD) {
        this.registeredPhD = registeredPhD;
        return this;
    }

    public void setRegisteredPhD(Boolean registeredPhD) {
        this.registeredPhD = registeredPhD;
    }

    public Boolean isScholarship() {
        return scholarship;
    }

    public StudyInfo scholarship(Boolean scholarship) {
        this.scholarship = scholarship;
        return this;
    }

    public void setScholarship(Boolean scholarship) {
        this.scholarship = scholarship;
    }

    public User getPatron() {
        return patron;
    }

    public StudyInfo patron(User myUser) {
        this.patron = myUser;
        return this;
    }

    public void setPatron(User myUser) {
        this.patron = myUser;
    }

    public User getUser() {
        return user;
    }

    public StudyInfo user(User myUser) {
        this.user = myUser;
        return this;
    }

    public void setUser(User myUser) {
        this.user = myUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyInfo studyInfo = (StudyInfo) o;
        return Objects.equals(id, studyInfo.id) &&
            Objects.equals(studyYear, studyInfo.studyYear) &&
            Objects.equals(registeredPhD, studyInfo.registeredPhD) &&
            Objects.equals(scholarship, studyInfo.scholarship) &&
            Objects.equals(patron, studyInfo.patron) &&
            Objects.equals(user, studyInfo.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyYear, registeredPhD, scholarship);
    }

    @Override
    public String toString() {
        return "StudyInfo{" +
            "id=" + id +
            ", studyYear=" + studyYear +
            ", registeredPhD=" + registeredPhD +
            ", scholarship=" + scholarship +
            ", patron=" + patron +
            '}';
    }
}
