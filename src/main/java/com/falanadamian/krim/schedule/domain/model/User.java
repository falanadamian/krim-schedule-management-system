package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.Degree;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "identity", nullable = false, unique = true)
    private String identity;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "degree", nullable = false)
    private Degree degree;

    @Column(name = "reduction")
    private Integer reduction;

    @Column(name = "note")
    private String note;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_type", referencedColumnName = "type")})
    @BatchSize(size = 20)
    private Set<Role> roles = new HashSet<>();

    @NotNull
    @JoinColumn(name = "user_config")
    @OneToOne(optional = false)
    private UserConfig position;

    @JoinColumn(name = "limit_config")
    @OneToOne()
    private LimitConfig limitConfig;

    @JoinColumn(name = "additional_hours_config")
    @OneToOne()
    private AdditionalHoursConfig additionalHoursConfig;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "study_info")
    private StudyInfo studyInfo;

    @OneToMany(mappedBy = "parentUser")
    private Set<User> childUsers = new HashSet<>();

    @OneToMany(mappedBy = "responsibleTeacher")
    private Set<ModuleGeneralInformation> modulesInChargeoves = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_modules_in_management",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "modules_in_management_id", referencedColumnName = "id"))
    private Set<ModuleGeneralInformation> modulesInManagement = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Module> modules;

    @OneToMany(mappedBy = "assignee")
    @JsonIgnore
    private List<Schedule> schedulesAssignedTo = new ArrayList<>();

    @OneToMany(mappedBy = "assignor")
    @JsonIgnore
    private List<Schedule> schedulesAssignedBy = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("childUsers")
    private User parentUser;

    public Long getId() {
        return id;
    }

    public User id(Long id) {
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

    public User firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User lastName(String last) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public User identity(String identity) {
        this.identity = identity;
        return this;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Degree getDegree() {
        return degree;
    }

    public User degree(Degree degree) {
        this.degree = degree;
        return this;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isActivated() {
        return activated;
    }

    public User activated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User roles(Set<Role> roles){
        this.roles = roles;
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserConfig getPosition() {
        return position;
    }

    public void setPosition(UserConfig position) {
        this.position = position;
    }

    public StudyInfo getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(StudyInfo studyInfo) {
        this.studyInfo = studyInfo;
    }

    public LimitConfig getLimitConfig() {
        return limitConfig;
    }

    public void setLimitConfig(LimitConfig limitConfig) {
        this.limitConfig = limitConfig;
    }

    public AdditionalHoursConfig getAdditionalHoursConfig() {
        return additionalHoursConfig;
    }

    public void setAdditionalHoursConfig(AdditionalHoursConfig additionalHoursConfig) {
        this.additionalHoursConfig = additionalHoursConfig;
    }

    public Set<User> getChildUsers() {
        return childUsers;
    }

    public void setChildUsers(Set<User> childUsers) {
        this.childUsers = childUsers;
    }

    public Set<ModuleGeneralInformation> getModulesInChargeoves() {
        return modulesInChargeoves;
    }

    public void setModulesInChargeoves(Set<ModuleGeneralInformation> modulesInChargeoves) {
        this.modulesInChargeoves = modulesInChargeoves;
    }

    public Set<ModuleGeneralInformation> getModulesInManagement() {
        return modulesInManagement;
    }

    public void setModulesInManagement(Set<ModuleGeneralInformation> modulesInManagement) {
        this.modulesInManagement = modulesInManagement;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User parentUser) {
        this.parentUser = parentUser;
    }

    public List<Schedule> getSchedulesAssignedTo() {
        return schedulesAssignedTo;
    }

    public void setSchedulesAssignedTo(List<Schedule> schedulesAssignedTo) {
        this.schedulesAssignedTo = schedulesAssignedTo;
    }

    public List<Schedule> getSchedulesAssignedBy() {
        return schedulesAssignedBy;
    }

    public void setSchedulesAssignedBy(List<Schedule> schedulesAssignedBy) {
        this.schedulesAssignedBy = schedulesAssignedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return activated == user.activated &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(identity, user.identity) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                degree == user.degree &&
                Objects.equals(reduction, user.reduction) &&
                Objects.equals(note, user.note) &&
                Objects.equals(activationKey, user.activationKey) &&
                Objects.equals(resetKey, user.resetKey) &&
                Objects.equals(resetDate, user.resetDate) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(position, user.position) &&
                Objects.equals(studyInfo, user.studyInfo) &&
                Objects.equals(childUsers, user.childUsers) &&
                Objects.equals(modulesInChargeoves, user.modulesInChargeoves) &&
                Objects.equals(modulesInManagement, user.modulesInManagement) &&
                Objects.equals(modules, user.modules) &&
                Objects.equals(parentUser, user.parentUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, identity, username, email, password, degree, reduction, note, activated, activationKey, resetKey, resetDate, roles, position, childUsers, modulesInChargeoves, modulesInManagement, modules, parentUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", degree=" + degree +
                ", reduction=" + reduction +
                ", note='" + note + '\'' +
                ", activated=" + activated +
                ", activationKey='" + activationKey + '\'' +
                ", resetKey='" + resetKey + '\'' +
                ", resetDate=" + resetDate +
                ", roles=" + roles +
                ", position=" + position +
                ", studyInfo=" + studyInfo +
                '}';
    }
}
