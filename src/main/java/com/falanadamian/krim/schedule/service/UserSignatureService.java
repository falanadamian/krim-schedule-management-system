package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.UserSignatureDTO;
import com.falanadamian.krim.schedule.domain.mapper.UserSignatureMapper;
import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.UserSignature;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.repository.ModuleGeneralInformationRepository;
import com.falanadamian.krim.schedule.repository.UserSignatureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserSignatureService {

    private final UserSignatureRepository userSignatureRepository;
    private final UserSignatureMapper userSignatureMapper;
    private final ModuleGeneralInformationRepository moduleGeneralInformationRepository;

    public UserSignatureService(UserSignatureRepository userSignatureRepository, UserSignatureMapper userSignatureMapper, ModuleGeneralInformationRepository moduleGeneralInformationRepository) {
        this.userSignatureRepository = userSignatureRepository;
        this.userSignatureMapper = userSignatureMapper;
        this.moduleGeneralInformationRepository = moduleGeneralInformationRepository;
    }

    public UserSignature save(UserSignature userSignature) {
        userSignature = userSignatureRepository.save(userSignature);
        this.saveRelations(userSignature);
        return userSignature;
    }

    @Transactional
    protected void saveRelations(UserSignature userSignature) {
        if(Objects.nonNull(userSignature.getModulesInChargeover()) && !userSignature.getModulesInChargeover().isEmpty()) {
            userSignature.getModulesInChargeover().forEach(moduleInChargeover -> {
                moduleInChargeover.setResponsibleUserSignature(userSignature);
                moduleGeneralInformationRepository.save(moduleInChargeover);
            });
        }
        if(Objects.nonNull(userSignature.getModulesInManagement()) && !userSignature.getModulesInManagement().isEmpty()) {
            userSignature.getModulesInManagement().forEach(moduleInManagement -> {
                moduleInManagement.getAcademicUserSignatures().add(userSignature);
                moduleGeneralInformationRepository.save(moduleInManagement);
            });
        }
    }

    private void fetchRelations(UserSignature userSignature) {
        if(Objects.nonNull(userSignature.getModulesInChargeover()) && !userSignature.getModulesInChargeover().isEmpty()) {
            userSignature.setModulesInChargeover(userSignature.getModulesInChargeover().stream().map(moduleInChargeover -> {
                Optional<ModuleGeneralInformation> optionalModuleGeneralInformation = moduleGeneralInformationRepository.findById(moduleInChargeover.getId());
                if(optionalModuleGeneralInformation.isPresent()) {
                    ModuleGeneralInformation moduleGeneralInformation = optionalModuleGeneralInformation.get();
                    moduleGeneralInformation.setResponsibleUserSignature(userSignature);
                    return moduleGeneralInformation;
                } else {
                    throw new BadRequestException("Module general information with ID :" + moduleInChargeover.getId() + " does not exist");
                }
            }).collect(Collectors.toSet()));
        }
        if(Objects.nonNull(userSignature.getModulesInManagement()) && !userSignature.getModulesInManagement().isEmpty()) {
            userSignature.setModulesInManagement(userSignature.getModulesInManagement().stream().map(moduleInManagement -> {
                Optional<ModuleGeneralInformation> optionalModuleGeneralInformation = moduleGeneralInformationRepository.findById(moduleInManagement.getId());
                if(optionalModuleGeneralInformation.isPresent()) {
                    ModuleGeneralInformation moduleGeneralInformation = optionalModuleGeneralInformation.get();
                    moduleGeneralInformation.getAcademicUserSignatures().add(userSignature);
                    return moduleGeneralInformation;

                } else {
                    throw new BadRequestException("Module general information with ID :" + moduleInManagement.getId() + " does not exist");
                }
            }).collect(Collectors.toSet()));
        }
    }

    public UserSignatureDTO save(UserSignatureDTO userSignatureDTO) {
        UserSignature userSignature = this.userSignatureMapper.toEntity(userSignatureDTO);
        userSignature = userSignatureRepository.save(userSignature);
        userSignature = userSignatureRepository.findById(userSignature.getId()).get();
        this.fetchRelations(userSignature);
        return this.userSignatureMapper.toDto(userSignature);
    }

    @Transactional(readOnly = true)
    public List<UserSignatureDTO> findAll() {
        return userSignatureRepository
                .findAll()
                .stream()
                .map(this.userSignatureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UserSignatureDTO> findOne(Long id) {
        return userSignatureRepository
                .findById(id)
                .map(this.userSignatureMapper::toDto);
    }

    public Optional<UserSignature> findOneByEmail(String email) {
        return userSignatureRepository.findByEmail(email);
    }

    public void delete(Long id) {
        userSignatureRepository.deleteById(id);
    }


}
