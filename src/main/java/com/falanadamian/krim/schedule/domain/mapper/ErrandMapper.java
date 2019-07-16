package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ErrandMapper extends EntityMapper<ErrandDTO, Errand> {

//    @Mapping(source = "schedule.id", target = "scheduleId")
//    @Mapping(source = "schedules", target = "schedulesIds")
    @Mapping(source = "module.id", target = "moduleId")
//    @Mapping(source = "document.id", target = "documentId")
    ErrandDTO toDto(Errand errand);

    @InheritInverseConfiguration
    Errand toEntity(ErrandDTO errandDTO);

    /*default Long fromSchedule(Schedule schedule) {
        return schedule.getId();
    }

    default Schedule scheduleFromId(Long id) {
        Schedule schedule = new Schedule();
        schedule.setId(id);
        return schedule;
    }*/

}
