package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.Degree;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_signature")
public class UserSignature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "degree")
    private Degree degree;

    @OneToMany(mappedBy = "responsibleUserSignature")
    private Set<ModuleGeneralInformation> modulesInChargeover = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_signature_modules_in_management",
            joinColumns = @JoinColumn(name = "user_signature_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "module_in_management_id", referencedColumnName = "id"))
    private Set<ModuleGeneralInformation> modulesInManagement = new HashSet<>();

    public Long getId() {
        return id;
    }

    public UserSignature id(Long id) {
        this.id = id;
        return this;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Set<ModuleGeneralInformation> getModulesInChargeover() {
        return modulesInChargeover;
    }

    public void setModulesInChargeover(Set<ModuleGeneralInformation> modulesInChargeover) {
        this.modulesInChargeover = modulesInChargeover;
    }

    public Set<ModuleGeneralInformation> getModulesInManagement() {
        return modulesInManagement;
    }

    public void setModulesInManagement(Set<ModuleGeneralInformation> modulesInManagement) {
        this.modulesInManagement = modulesInManagement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignature that = (UserSignature) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                degree == that.degree &&
                Objects.equals(modulesInChargeover, that.modulesInChargeover) &&
                Objects.equals(modulesInManagement, that.modulesInManagement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, degree);
    }

    @Override
    public String toString() {
        return "UserSignature{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", degree=" + degree +
                '}';
    }
}
