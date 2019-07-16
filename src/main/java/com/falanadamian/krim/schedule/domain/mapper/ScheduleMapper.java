package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ClassesMapper.class})
public interface ScheduleMapper extends EntityMapper<ScheduleDTO, Schedule> {

    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "assignor.id", target = "assignorId")
    @Mapping(source = "classes", target = "classes")
    @Mapping(source = "module", target = "moduleId")
//    @Mapping(source = "errands", target = "errands")
//    @Mapping(source = "errand", target = "errand")
    ScheduleDTO toDto(Schedule schedule);

    @InheritInverseConfiguration
    Schedule toEntity(ScheduleDTO scheduleDTO);

    default Long fromModule(Module module) {
        return Objects.nonNull(module) ? module.getId() : null;
    }

    default Module userFromId(Long id) {
        if(Objects.isNull(id))
            return null;
        return new Module().id(id);
    }
}
