package com.falanadamian.krim.schedule.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StateTrackerDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate addTime;

    private LocalDate deleteTime;

    @NotNull
    private Boolean deleted;

    private Long myUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDate addTime) {
        this.addTime = addTime;
    }

    public LocalDate getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDate deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(Long myUserId) {
        this.myUserId = myUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTrackerDTO that = (StateTrackerDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(addTime, that.addTime) &&
            Objects.equals(deleteTime, that.deleteTime) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(myUserId, that.myUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addTime, deleteTime, deleted, myUserId);
    }

    @Override
    public String toString() {
        return "StateTrackerDTO{" +
            "id=" + id +
            ", addTime=" + addTime +
            ", deleteTime=" + deleteTime +
            ", deleted=" + deleted +
            ", myUserId=" + myUserId +
            '}';
    }
}
