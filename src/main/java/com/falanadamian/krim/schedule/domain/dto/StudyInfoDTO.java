package com.falanadamian.krim.schedule.domain.dto;

import java.io.Serializable;
import java.util.Objects;

public class StudyInfoDTO implements Serializable {

    private Long id;

    private Integer studyYear;

    private Boolean registeredPhD;

    private Boolean scholarship;

    private Long patronId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(Integer studyYear) {
        this.studyYear = studyYear;
    }

    public Boolean isRegisteredPhD() {
        return registeredPhD;
    }

    public void setRegisteredPhD(Boolean registeredPhD) {
        this.registeredPhD = registeredPhD;
    }

    public Boolean isScholarship() {
        return scholarship;
    }

    public void setScholarship(Boolean scholarship) {
        this.scholarship = scholarship;
    }

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long myUserId) {
        this.patronId = myUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyInfoDTO that = (StudyInfoDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(studyYear, that.studyYear) &&
            Objects.equals(registeredPhD, that.registeredPhD) &&
            Objects.equals(scholarship, that.scholarship) &&
            Objects.equals(patronId, that.patronId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studyYear, registeredPhD, scholarship, patronId);
    }

    @Override
    public String toString() {
        return "StudyInfoDTO{" +
            "id=" + id +
            ", studyYear=" + studyYear +
            ", registeredPhD=" + registeredPhD +
            ", scholarship=" + scholarship +
            ", patronId=" + patronId +
            '}';
    }
}
