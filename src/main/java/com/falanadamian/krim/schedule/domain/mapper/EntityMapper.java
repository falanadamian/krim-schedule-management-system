package com.falanadamian.krim.schedule.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface EntityMapper <D, E> {

    E toEntity(D dto);

    @InheritInverseConfiguration
    D toDto(E entity);

    List <E> toEntity(List<D> dtoList);

    @InheritInverseConfiguration
    List <D> toDto(List<E> entityList);
}
