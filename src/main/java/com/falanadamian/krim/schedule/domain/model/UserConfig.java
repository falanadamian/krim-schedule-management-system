package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.Position;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_config")
public class UserConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;

    @NotNull
    @Column(name = "pensum", nullable = false)
    private Integer pensum;

    @NotNull
    @Column(name = "pensum_limit", nullable = false)
    private Integer pensumLimit;

    @NotNull
    @Column(name = "overtime_rate_full_time", nullable = false)
    private Integer overtimeRateFullTime;

    @NotNull
    @Column(name = "overtime_rate_part_time", nullable = false)
    private Integer overtimeRatePartTime;

    public Position getPosition() {
        return position;
    }

    public UserConfig position(Position position) {
        this.position = position;
        return this;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getPensum() {
        return pensum;
    }

    public UserConfig pensum(Integer pensum) {
        this.pensum = pensum;
        return this;
    }

    public void setPensum(Integer pensum) {
        this.pensum = pensum;
    }

    public Integer getPensumLimit() {
        return pensumLimit;
    }

    public UserConfig pensumLimit(Integer pensumLimit) {
        this.pensumLimit = pensumLimit;
        return this;
    }

    public void setPensumLimit(Integer pensumLimit) {
        this.pensumLimit = pensumLimit;
    }

    public Integer getOvertimeRateFullTime() {
        return overtimeRateFullTime;
    }

    public UserConfig overtimeRateFullTime(Integer overtimeRateFullTime) {
        this.overtimeRateFullTime = overtimeRateFullTime;
        return this;
    }

    public void setOvertimeRateFullTime(Integer overtimeRateFullTime) {
        this.overtimeRateFullTime = overtimeRateFullTime;
    }

    public Integer getOvertimeRatePartTime() {
        return overtimeRatePartTime;
    }

    public UserConfig overtimeRatePartTime(Integer overtimeRatePartTime) {
        this.overtimeRatePartTime = overtimeRatePartTime;
        return this;
    }

    public void setOvertimeRatePartTime(Integer overtimeRatePartTime) {
        this.overtimeRatePartTime = overtimeRatePartTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConfig that = (UserConfig) o;
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
        return "UserConfig{" +
                "position=" + position +
                ", pensum=" + pensum +
                ", pensumLimit=" + pensumLimit +
                ", overtimeRateFullTime=" + overtimeRateFullTime +
                ", overtimeRatePartTime=" + overtimeRatePartTime +
                '}';
    }
}
