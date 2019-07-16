package com.falanadamian.krim.schedule.domain.mapper;

import com.falanadamian.krim.schedule.domain.dto.UserSignatureDTO;
import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.UserSignature;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ModuleGeneralInformationMapper.class})
public interface UserSignatureMapper {

    @Mapping(source = "modulesInChargeover", target = "modulesInChargeoverIds")
    @Mapping(source = "modulesInManagement", target = "modulesInManagementIds")
    UserSignatureDTO toDto(UserSignature userSignature);

    @InheritInverseConfiguration
    UserSignature toEntity(UserSignatureDTO userSignatureDTO);

    List<UserSignature> toEntity(List<UserSignatureDTO> dtoList);

    @InheritInverseConfiguration
    List<UserSignatureDTO> toDto(List<UserSignature> entityList);

    default Long fromModuleGeneralInformation(ModuleGeneralInformation moduleGeneralInformation) {
        return moduleGeneralInformation.getId();
    }

    default Set<ModuleGeneralInformation> fromModuleGeneralInformationIds(Set<Long> moduleGeneralInformationIds) {
        Set<ModuleGeneralInformation> moduleGeneralInformations = new HashSet<>();
        moduleGeneralInformationIds.forEach(moduleGeneralInformationId -> {
            ModuleGeneralInformation moduleGeneralInformation = new ModuleGeneralInformation();
            moduleGeneralInformation.setId(moduleGeneralInformationId);
            moduleGeneralInformations.add(moduleGeneralInformation);
        });
        return moduleGeneralInformations;
    }

}
