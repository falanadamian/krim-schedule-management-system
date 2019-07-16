package com.falanadamian.krim.schedule.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class LimitConfigDTO implements Serializable {

    private Long id;

    @NotNull
    private Long partTime;

    @NotNull
    private Long fullTime;

    @NotNull
    private Long fullTimePolish;

    @NotNull
    private Long fullTimeEnglish;

    @NotNull
    private Long bachelorThesis;

    @NotNull
    private Long masterThesis;

    @NotNull
    private Long hoursForExamPerYear;

    @NotNull
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

    public void setPartTime(Long partTime) {
        this.partTime = partTime;
    }

    public Long getFullTime() {
        return fullTime;
    }

    public void setFullTime(Long fullTime) {
        this.fullTime = fullTime;
    }

    public Long getFullTimePolish() {
        return fullTimePolish;
    }

    public void setFullTimePolish(Long fullTimePolish) {
        this.fullTimePolish = fullTimePolish;
    }

    public Long getFullTimeEnglish() {
        return fullTimeEnglish;
    }

    public void setFullTimeEnglish(Long fullTimeEnglish) {
        this.fullTimeEnglish = fullTimeEnglish;
    }

    public Long getBachelorThesis() {
        return bachelorThesis;
    }

    public void setBachelorThesis(Long bachelorThesis) {
        this.bachelorThesis = bachelorThesis;
    }

    public Long getMasterThesis() {
        return masterThesis;
    }

    public void setMasterThesis(Long masterThesis) {
        this.masterThesis = masterThesis;
    }

    public Long getHoursForExamPerYear() {
        return hoursForExamPerYear;
    }

    public void setHoursForExamPerYear(Long hoursForExamPerYear) {
        this.hoursForExamPerYear = hoursForExamPerYear;
    }

    public Long getPhysicalHours() {
        return physicalHours;
    }

    public void setPhysicalHours(Long physicalHours) {
        this.physicalHours = physicalHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitConfigDTO that = (LimitConfigDTO) o;
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
        return "LimitConfigDTO{" +
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
