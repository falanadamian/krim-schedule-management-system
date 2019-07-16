package com.falanadamian.krim.schedule.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "limit_config")
public class LimitConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "part_time", nullable = false)
    private Long partTime;

    @NotNull
    @Column(name = "full_time", nullable = false)
    private Long fullTime;

    @NotNull
    @Column(name = "full_time_polish", nullable = false)
    private Long fullTimePolish;

    @NotNull
    @Column(name = "full_time_english", nullable = false)
    private Long fullTimeEnglish;

    @NotNull
    @Column(name = "bachelor_thesis", nullable = false)
    private Long bachelorThesis;

    @NotNull
    @Column(name = "master_thesis", nullable = false)
    private Long masterThesis;

    @NotNull
    @Column(name = "hours_for_exam_per_year", nullable = false)
    private Long hoursForExamPerYear;

    @NotNull
    @Column(name = "physical_hours", nullable = false)
    private Long physicalHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPartTime() {
        return partTime;
    }

    public LimitConfig partTime(Long partTime) {
        this.partTime = partTime;
        return this;
    }

    public void setPartTime(Long partTime) {
        this.partTime = partTime;
    }

    public Long getFullTime() {
        return fullTime;
    }

    public LimitConfig fullTime(Long fullTime) {
        this.fullTime = fullTime;
        return this;
    }

    public void setFullTime(Long fullTime) {
        this.fullTime = fullTime;
    }

    public Long getFullTimePolish() {
        return fullTimePolish;
    }

    public LimitConfig fullTimePolish(Long fullTimePolish) {
        this.fullTimePolish = fullTimePolish;
        return this;
    }

    public void setFullTimePolish(Long fullTimePolish) {
        this.fullTimePolish = fullTimePolish;
    }

    public Long getFullTimeEnglish() {
        return fullTimeEnglish;
    }

    public LimitConfig fullTimeEnglish(Long fullTimeEnglish) {
        this.fullTimeEnglish = fullTimeEnglish;
        return this;
    }

    public void setFullTimeEnglish(Long fullTimeEnglish) {
        this.fullTimeEnglish = fullTimeEnglish;
    }

    public Long getBachelorThesis() {
        return bachelorThesis;
    }

    public LimitConfig bachelorThesis(Long bachelorThesis) {
        this.bachelorThesis = bachelorThesis;
        return this;
    }

    public void setBachelorThesis(Long bachelorThesis) {
        this.bachelorThesis = bachelorThesis;
    }

    public Long getMasterThesis() {
        return masterThesis;
    }

    public LimitConfig masterThesis(Long masterThesis) {
        this.masterThesis = masterThesis;
        return this;
    }

    public void setMasterThesis(Long masterThesis) {
        this.masterThesis = masterThesis;
    }

    public Long getHoursForExamPerYear() {
        return hoursForExamPerYear;
    }

    public LimitConfig hoursForExamPerYear(Long hoursForExamPerYear) {
        this.hoursForExamPerYear = hoursForExamPerYear;
        return this;
    }

    public void setHoursForExamPerYear(Long hoursForExamPerYear) {
        this.hoursForExamPerYear = hoursForExamPerYear;
    }

    public Long getPhysicalHours() {
        return physicalHours;
    }

    public LimitConfig physicalHours(Long physicalHours) {
        this.physicalHours = physicalHours;
        return this;
    }

    public void setPhysicalHours(Long physicalHours) {
        this.physicalHours = physicalHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitConfig that = (LimitConfig) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(partTime, that.partTime) &&
            Objects.equals(fullTime, that.fullTime) &&
            Objects.equals(fullTimePolish, that.fullTimePolish) &&
            Objects.equals(fullTimeEnglish, that.fullTimeEnglish) &&
            Objects.equals(bachelorThesis, that.bachelorThesis) &&
            Objects.equals(masterThesis, that.masterThesis) &&
            Objects.equals(hoursForExamPerYear, that.hoursForExamPerYear) &&
            Objects.equals(physicalHours, that.physicalHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partTime, fullTime, fullTimePolish, fullTimeEnglish, bachelorThesis, masterThesis, hoursForExamPerYear, physicalHours);
    }

    @Override
    public String toString() {
        return "LimitConfig{" +
            "id=" + id +
            ", partTime=" + partTime +
            ", fullTime=" + fullTime +
            ", fullTimePolish=" + fullTimePolish +
            ", fullTimeEnglish=" + fullTimeEnglish +
            ", bachelorThesis=" + bachelorThesis +
            ", masterThesis=" + masterThesis +
            ", hoursForExamPerYear=" + hoursForExamPerYear +
            ", physicalHours=" + physicalHours +
            '}';
    }
}
