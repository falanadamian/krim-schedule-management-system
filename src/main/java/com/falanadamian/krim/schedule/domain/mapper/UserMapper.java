package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring", uses = {UserConfigMapper.class, StudyInfoMapper.class, ModuleGeneralInformationMapper.class, RoleMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Mapping(source = "position.position", target = "position")
    @Mapping(source = "studyInfo.id", target = "studyInfoId")
    @Mapping(source = "studyInfo", target = "studyInfo")
    @Mapping(source = "parentUser.id", target = "parentUserId")
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "modulesInManagement", target = "modulesInManagementIds")
    @Mapping(source = "modulesInChargeoves", target = "modulesInChargeovesIds")
    @Mapping(source = "position", target = "userConfig")
    @Mapping(source = "schedulesAssignedTo", target = "schedulesAssignedToIds")
    @Mapping(source = "schedulesAssignedBy", target = "schedulesAssignedByIds")
    UserDTO toDto(User user);

    @Mapping(source = "studyInfoId", target = "studyInfo.id")
    @Mapping(source = "studyInfo", target = "studyInfo")
//    @Mapping(target = "files", ignore = true)
    @Mapping(target = "childUsers", ignore = true)
//    @Mapping(target = "modulesInChargeoves", ignore = true)
    @Mapping(target = "modules", ignore = true)
    @Mapping(source = "parentUserId", target = "parentUser.id")
    @Mapping(source = "userConfig", target = "position")
    User toEntity(UserDTO userDTO);

    default Long fromModuleGeneralInformation(ModuleGeneralInformation moduleGeneralInformation) {
        return moduleGeneralInformation.getId();
    }

    default Long fromSchedule(Schedule schedule) {
        return Objects.nonNull(schedule) ? schedule.getId() : null;
    }

    default List<Schedule> fromScheduleIds(List<Long> scheduleIds) {
        if(Objects.isNull(scheduleIds))
            return null;

        List<Schedule> schedules = new ArrayList<>();
        scheduleIds.forEach( scheduleId -> {
            Schedule schedule = new Schedule();
            schedule.setId(scheduleId);
            schedules.add(schedule);
        });
        return schedules;
    }



}
