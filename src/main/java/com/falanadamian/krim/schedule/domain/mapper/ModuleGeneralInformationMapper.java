package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.model.*;
import org.mapstruct.*;

import java.util.*;

@Mapper(componentModel = "spring", uses = { UserMapper.class, UserSignatureMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ModuleGeneralInformationMapper {

    @Mapping(source = "module", target = "moduleId")
    @Mapping(source = "responsibleUserSignature", target = "responsibleUserSignatureId")
    @Mapping(source = "responsibleTeacher", target = "responsibleTeacherId")
    @Mapping(source = "academicUserSignatures", target = "academicUserSignaturesIds")
    @Mapping(source = "academicTeachers", target = "academicTeachersIds")
    ModuleGeneralInformationDTO toDto(ModuleGeneralInformation moduleGeneralInformation);

    @InheritInverseConfiguration
    ModuleGeneralInformation toEntity(ModuleGeneralInformationDTO moduleGeneralInformationDTO);

    List<ModuleGeneralInformation> toEntity(List<ModuleGeneralInformationDTO> dtoList);

    @InheritInverseConfiguration
    List <ModuleGeneralInformationDTO> toDto(List<ModuleGeneralInformation> entityList);

    default Long fromModule(Module module) {
        return Objects.nonNull(module) ? module.getId() : null;
    }

    default Module moduleFromId(Long id) {
        if(Objects.isNull(id))
            return null;
        return new Module().id(id);
    }

    default Long fromUser(User user) {
        return Objects.nonNull(user) ? user.getId() : null;
    }

    default User userFromId(Long id) {
        if(Objects.isNull(id))
            return null;
        return new User().id(id);
    }

    default Long fromUserSignature(UserSignature userSignature) {
        return Objects.nonNull(userSignature) ? userSignature.getId() : null;
    }

    default UserSignature userSignatureFromId(Long id) {
        if(Objects.isNull(id))
            return null;
        return new UserSignature().id(id);
    }

    default List<UserSignature> fromUserSignaturesIds(List<Long> userSignaturesIds) {
        if(Objects.isNull(userSignaturesIds))
            return null;

        List<UserSignature> userSignatures = new ArrayList<>();
        userSignaturesIds.forEach( userSignatureId -> {
            UserSignature userSignature = new UserSignature();
            userSignature.setId(userSignatureId);
            userSignatures.add(userSignature);
        });
        return userSignatures;
    }

    default Set<User> fromUserIds(List<Long> userIds) {
        if(Objects.isNull(userIds))
            return null;

        Set<User> users = new HashSet<>();
        userIds.forEach( userSignatureId -> {
            User user = new User();
            user.setId(userSignatureId);
            users.add(user);
        });
        return users;
    }

}
