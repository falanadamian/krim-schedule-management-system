package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.enumeration.Degree;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class UserSignatureDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Degree degree;

    @Email
    @NotNull
    @Size(min = 6, max = 256)
    private String email;

    private Set<Long> modulesInChargeoverIds = new HashSet<>();

    private Set<Long> modulesInManagementIds = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getModulesInChargeoverIds() {
        return modulesInChargeoverIds;
    }

    public void setModulesInChargeoverIds(Set<Long> modulesInChargeoverIds) {
        this.modulesInChargeoverIds = modulesInChargeoverIds;
    }

    public Set<Long> getModulesInManagementIds() {
        return modulesInManagementIds;
    }

    public void setModulesInManagementIds(Set<Long> modulesInManagementIds) {
        this.modulesInManagementIds = modulesInManagementIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignatureDTO that = (UserSignatureDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                degree == that.degree &&
                Objects.equals(email, that.email) &&
                Objects.equals(modulesInChargeoverIds, that.modulesInChargeoverIds) &&
                Objects.equals(modulesInManagementIds, that.modulesInManagementIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, degree, email, modulesInChargeoverIds, modulesInManagementIds);
    }

    @Override
    public String toString() {
        return "UserSignatureDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", degree=" + degree +
                ", email='" + email + '\'' +
                ", modulesInChargeoverIds=" + modulesInChargeoverIds +
                ", modulesInManagementIds=" + modulesInManagementIds +
                '}';
    }
}
