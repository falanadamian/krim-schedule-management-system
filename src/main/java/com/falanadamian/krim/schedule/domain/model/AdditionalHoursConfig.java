package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.AdditionalHoursReason;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "additional_hours_config")
public class AdditionalHoursConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "reason", nullable = false)
    private AdditionalHoursReason reason;

    @NotNull
    @Column(name = "coefficient", nullable = false)
    private Long coefficient;

    @NotNull
    @Column(name = "includes_limit", nullable = false)
    private Boolean includesLimit;

    @Column(name = "note")
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

    public AdditionalHoursConfig reason(AdditionalHoursReason reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(AdditionalHoursReason reason) {
        this.reason = reason;
    }

    public Long getCoefficient() {
        return coefficient;
    }

    public AdditionalHoursConfig coefficient(Long coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    public void setCoefficient(Long coefficient) {
        this.coefficient = coefficient;
    }

    public Boolean isIncludesLimit() {
        return includesLimit;
    }

    public AdditionalHoursConfig includesLimit(Boolean includesLimit) {
        this.includesLimit = includesLimit;
        return this;
    }

    public void setIncludesLimit(Boolean includesLimit) {
        this.includesLimit = includesLimit;
    }

    public String getNote() {
        return note;
    }

    public AdditionalHoursConfig note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdditionalHoursConfig that = (AdditionalHoursConfig) o;
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
        return "AdditionalHoursConfig{" +
            "id=" + id +
            ", reason=" + reason +
            ", coefficient=" + coefficient +
            ", includesLimit=" + includesLimit +
            ", note='" + note + '\'' +
            '}';
    }
}
