package com.falanadamian.krim.schedule.domain.model;

import com.falanadamian.krim.schedule.domain.model.enumeration.RoleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private RoleType type;

    public Role(){}

    public Role(@NotNull RoleType type) {
        this.type = type;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return type == role.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Role{" +
                "type=" + type +
                '}';
    }
}
