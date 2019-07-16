package com.falanadamian.krim.schedule.domain.dto;

import com.falanadamian.krim.schedule.domain.model.Role;

import java.io.Serializable;
import java.util.Objects;

public class RoleDTO implements Serializable {

    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public RoleDTO(){}

    public RoleDTO(String roleType) {
        this.roleType = roleType;
    }

    public RoleDTO(Role role){
        this.roleType = role.getType().getRoleType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(roleType, roleDTO.roleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleType);
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleType='" + roleType + '\'' +
                '}';
    }
}
