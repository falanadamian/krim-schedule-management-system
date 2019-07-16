package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.RoleDTO;
import com.falanadamian.krim.schedule.domain.model.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {

    @Mapping(source = "type", target = "roleType")
    RoleDTO toDto(Role role);

    @InheritInverseConfiguration
    Role toEntity(RoleDTO roleDTO);

    Set<Role> toEntity(Set<RoleDTO> rolesDto);

    Set<RoleDTO> toDto(Set<Role> roles);

}
