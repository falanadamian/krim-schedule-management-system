package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.enumeration.Position;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UserConfigDTO implements Serializable {

    @NotNull
    private Position position;

    @NotNull
    private Integer pensum;

    @NotNull
    private Integer pensumLimit;

    @NotNull
    private Integer overtimeRateFullTime;

    @NotNull
    private Integer overtimeRatePartTime;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getPensum() {
        return pensum;
    }

    public void setPensum(Integer pensum) {
        this.pensum = pensum;
    }

    public Integer getPensumLimit() {
        return pensumLimit;
    }

    public void setPensumLimit(Integer pensumLimit) {
        this.pensumLimit = pensumLimit;
    }

    public Integer getOvertimeRateFullTime() {
        return overtimeRateFullTime;
    }

    public void setOvertimeRateFullTime(Integer overtimeRateFullTime) {
        this.overtimeRateFullTime = overtimeRateFullTime;
    }

    public Integer getOvertimeRatePartTime() {
        return overtimeRatePartTime;
    }

    public void setOvertimeRatePartTime(Integer overtimeRatePartTime) {
        this.overtimeRatePartTime = overtimeRatePartTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConfigDTO that = (UserConfigDTO) o;
        return position == that.position &&
            Objects.equals(pensum, that.pensum) &&
            Objects.equals(pensumLimit, that.pensumLimit) &&
            Objects.equals(overtimeRateFullTime, that.overtimeRateFullTime) &&
            Objects.equals(overtimeRatePartTime, that.overtimeRatePartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, pensum, pensumLimit, overtimeRateFullTime, overtimeRatePartTime);
    }

    @Override
    public String toString() {
        return "UserConfigDTO{" +
            ", position=" + position +
            ", pensum=" + pensum +
            ", pensumLimit=" + pensumLimit +
            ", overtimeRateFullTime=" + overtimeRateFullTime +
            ", overtimeRatePartTime=" + overtimeRatePartTime +
            '}';
    }
}
