package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.config.Constants;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.domain.model.enumeration.Degree;
import com.falanadamian.krim.schedule.domain.model.enumeration.Position;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String identity;

    @NotNull
    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @Email
    @NotNull
    @Size(min = 6, max = 256)
    private String email;

    @NotNull
    private String password;

    private boolean activated = false;

    @NotNull
    private Degree degree;

    private Integer reduction;

    private String note;

    private Long positionId;

    private Position position;

    private String positionPosition;

    private Long studyInfoId;

    private Set<Long> modulesInChargeovesIds = new HashSet<>();

    private Set<Long> modulesInManagementIds = new HashSet<>();

    private Long parentUserId;

    private Set<RoleDTO> roles;

    private UserConfigDTO userConfig;

    private StudyInfoDTO studyInfo;

    private List<Long> schedulesAssignedToIds = new ArrayList<>();

    private List<Long> schedulesAssignedByIds = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.roles = user.getRoles()
                .stream()
                .map(RoleDTO::new)
                .collect(Collectors.toSet());
    }

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Degree getDegree() {
        return degree;
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

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPositionPosition() {
        return positionPosition;
    }

    public void setPositionPosition(String positionPosition) {
        this.positionPosition = positionPosition;
    }

    public Long getStudyInfoId() {
        return studyInfoId;
    }

    public void setStudyInfoId(Long studyInfoId) {
        this.studyInfoId = studyInfoId;
    }

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public UserConfigDTO getUserConfig() {
        return userConfig;
    }

    public void setUserConfig(UserConfigDTO userConfig) {
        this.userConfig = userConfig;
    }

    public Set<Long> getModulesInChargeovesIds() {
        return modulesInChargeovesIds;
    }

    public void setModulesInChargeovesIds(Set<Long> modulesInChargeovesIds) {
        this.modulesInChargeovesIds = modulesInChargeovesIds;
    }

    public Set<Long> getModulesInManagementIds() {
        return modulesInManagementIds;
    }

    public void setModulesInManagementIds(Set<Long> modulesInManagementIds) {
        this.modulesInManagementIds = modulesInManagementIds;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public StudyInfoDTO getStudyInfo() {
        return studyInfo;
    }

    public void setStudyInfo(StudyInfoDTO studyInfo) {
        this.studyInfo = studyInfo;
    }

    public List<Long> getSchedulesAssignedToIds() {
        return schedulesAssignedToIds;
    }

    public void setSchedulesAssignedToIds(List<Long> schedulesAssignedToIds) {
        this.schedulesAssignedToIds = schedulesAssignedToIds;
    }

    public List<Long> getSchedulesAssignedByIds() {
        return schedulesAssignedByIds;
    }

    public void setSchedulesAssignedByIds(List<Long> schedulesAssignedByIds) {
        this.schedulesAssignedByIds = schedulesAssignedByIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return activated == userDTO.activated &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(identity, userDTO.identity) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(password, userDTO.password) &&
                degree == userDTO.degree &&
                Objects.equals(reduction, userDTO.reduction) &&
                Objects.equals(note, userDTO.note) &&
                Objects.equals(positionId, userDTO.positionId) &&
                position == userDTO.position &&
                Objects.equals(positionPosition, userDTO.positionPosition) &&
                Objects.equals(studyInfoId, userDTO.studyInfoId) &&
                Objects.equals(modulesInChargeovesIds, userDTO.modulesInChargeovesIds) &&
                Objects.equals(modulesInManagementIds, userDTO.modulesInManagementIds) &&
                Objects.equals(parentUserId, userDTO.parentUserId) &&
                Objects.equals(roles, userDTO.roles) &&
                Objects.equals(userConfig, userDTO.userConfig);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, identity, username, email, password, activated, degree, reduction, note, positionId, position, positionPosition, studyInfoId, modulesInChargeovesIds, modulesInManagementIds, parentUserId, roles, userConfig);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identity='" + identity + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", activated=" + activated +
                ", degree=" + degree +
                ", reduction=" + reduction +
                ", note='" + note + '\'' +
                ", positionId=" + positionId +
                ", position=" + position +
                ", positionPosition='" + positionPosition + '\'' +
                ", studyInfoId=" + studyInfoId +
                ", modulesInChargeovesIds=" + modulesInChargeovesIds +
                ", modulesInManagementIds=" + modulesInManagementIds +
                ", parentUserId=" + parentUserId +
                ", roles=" + roles +
                ", userConfig=" + userConfig +
                '}';
    }
}
