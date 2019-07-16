package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ClassesMapper.class, ClassLimitMapper.class, ModuleGeneralInformationMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ModuleMapper {

    @Autowired
    ModuleGeneralInformationMapper moduleGeneralInformationMapper;

    @Mappings({
        @Mapping(source = "user", target = "userId"),
        @Mapping(source = "classes", target = "classes"),
        @Mapping(source = "moduleGeneralInformation.id", target = "moduleGeneralInformationId"),
        @Mapping(source = "classLimit", target = "classLimit"),
        @Mapping(source = "errands", target = "errandIds"),
        @Mapping(source = "schedules", target = "scheduleIds"),
//        @Mapping(ignore = true, target = "moduleGeneralInformation")
    })
    public abstract ModuleDTO toDto(Module module);

    @InheritInverseConfiguration
    public abstract Module toEntity(ModuleDTO moduleDTO);

    public abstract List<Module> toEntity(List<ModuleDTO> dtoList);

    @InheritInverseConfiguration
    public abstract List <ModuleDTO> toDto(List<Module> entityList);

    @AfterMapping
    public void afterMapping(ModuleDTO moduleDTO, @MappingTarget Module module) {
        /*if(Objects.nonNull(moduleDTO.getModuleGeneralInformation())){
            module.setModuleGeneralInformation(moduleGeneralInformationMapper.toEntity(moduleDTO.getModuleGeneralInformation()));
        }*/
//        module.getModuleGeneralInformation().getResponsibleTeacher().setId(moduleDTO.getModuleGeneralInformation().getResponsibleTeacherId());
    }

    public Long fromErrand(Errand errand) {
        return Objects.nonNull(errand) ? errand.getId() : null;
    }

    public Set<Errand> fromErrandIds(Set<Long> errandIds) {
        if(Objects.isNull(errandIds))
            return null;

        Set<Errand> errands = new HashSet<>();
        errandIds.forEach( errandId -> {
            Errand errand = new Errand();
            errand.setId(errandId);
            errands.add(errand);
        });
        return errands;
    }

    public Long fromSchedule(Schedule schedule) {
        return Objects.nonNull(schedule) ? schedule.getId() : null;
    }

    public Set<Schedule> fromScheduleIds(Set<Long> scheduleIds) {
        if(Objects.isNull(scheduleIds))
            return null;

        Set<Schedule> schedules = new HashSet<>();
        scheduleIds.forEach( scheduleId -> {
            Schedule schedule = new Schedule();
            schedule.setId(scheduleId);
            schedules.add(schedule);
        });
        return schedules;
    }



}
