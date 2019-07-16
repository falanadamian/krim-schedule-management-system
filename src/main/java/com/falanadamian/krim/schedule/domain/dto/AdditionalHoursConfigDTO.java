package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.enumeration.AdditionalHoursReason;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class AdditionalHoursConfigDTO implements Serializable {

    private Long id;

    @NotNull
    private AdditionalHoursReason reason;

    @NotNull
    private Long coefficient;

    @NotNull
    private Boolean includesLimit;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdditionalHoursReason getReason() {
        return reason;
    }

    public void setReason(AdditionalHoursReason reason) {
        this.reason = reason;
    }

    public Long getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Long coefficient) {
        this.coefficient = coefficient;
    }

    public Boolean isIncludesLimit() {
        return includesLimit;
    }

    public void setIncludesLimit(Boolean includesLimit) {
        this.includesLimit = includesLimit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalHoursConfigDTO that = (AdditionalHoursConfigDTO) o;
        return Objects.equals(id, that.id) &&
            reason == that.reason &&
            Objects.equals(coefficient, that.coefficient) &&
            Objects.equals(includesLimit, that.includesLimit) &&
            Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason, coefficient, includesLimit, note);
    }

    @Override
    public String toString() {
        return "AdditionalHoursConfigDTO{" +
            "id=" + id +
            ", reason=" + reason +
            ", coefficient=" + coefficient +
            ", includesLimit=" + includesLimit +
            ", note='" + note + '\'' +
            '}';
    }
}
