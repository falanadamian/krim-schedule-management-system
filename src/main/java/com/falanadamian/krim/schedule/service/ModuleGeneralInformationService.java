package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.ModuleGeneralInformationDTO;
import com.falanadamian.krim.schedule.domain.mapper.ModuleGeneralInformationMapper;
import com.falanadamian.krim.schedule.domain.model.Module;
import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.domain.model.UserSignature;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.repository.ModuleGeneralInformationRepository;
import com.falanadamian.krim.schedule.repository.ModuleRepository;
import com.falanadamian.krim.schedule.repository.UserRepository;
import com.falanadamian.krim.schedule.repository.UserSignatureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ModuleGeneralInformationService {

    private final ModuleGeneralInformationRepository moduleGeneralInformationRepository;
    private final ModuleGeneralInformationMapper moduleGeneralInformationMapper;
    private final ModuleRepository moduleRepository;
    private final UserSignatureRepository userSignatureRepository;
    private final UserRepository userRepository;

    public ModuleGeneralInformationService(ModuleGeneralInformationRepository moduleGeneralInformationRepository,
                                           ModuleGeneralInformationMapper moduleGeneralInformationMapper,
                                           ModuleRepository moduleRepository,
                                           UserSignatureRepository userSignatureRepository,
                                           UserRepository userRepository
    ) {
        this.moduleGeneralInformationRepository = moduleGeneralInformationRepository;
        this.moduleGeneralInformationMapper = moduleGeneralInformationMapper;
        this.moduleRepository = moduleRepository;
        this.userSignatureRepository = userSignatureRepository;
        this.userRepository = userRepository;
    }

    protected void fetchRelations(ModuleGeneralInformation moduleGeneralInformation) {
        if(Objects.nonNull(moduleGeneralInformation.getModule())) {
            Optional<Module> optionalModule = moduleRepository.findById(moduleGeneralInformation.getModule().getId());
            if(optionalModule.isPresent()) {
                Module module = optionalModule.get();
                module.getModuleGeneralInformation().setModule(null);
                module.setModuleGeneralInformation(moduleGeneralInformation);
                moduleGeneralInformation.setModule(module);
            } else {
                throw new BadRequestException("Module with ID :" + moduleGeneralInformation.getModule().getId() + " does not exist");
            }
        }

        if(Objects.nonNull(moduleGeneralInformation.getResponsibleUserSignature())) {
            Optional<UserSignature> optionalUserSignature = userSignatureRepository.findById(moduleGeneralInformation.getResponsibleUserSignature().getId());
            if(optionalUserSignature.isPresent()) {
                UserSignature userSignature = optionalUserSignature.get();
                userSignature.getModulesInChargeover().add(moduleGeneralInformation);
                moduleGeneralInformation.setResponsibleUserSignature(userSignature);
                userSignatureRepository.save(userSignature);
            } else {
                throw new BadRequestException("UserSignature with ID :" + moduleGeneralInformation.getModule().getId() + " does not exist");
            }
        }

        if(Objects.nonNull(moduleGeneralInformation.getResponsibleTeacher())) {
            Optional<User> optionalUser = userRepository.findById(moduleGeneralInformation.getResponsibleTeacher().getId());
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.getModulesInChargeoves().add(moduleGeneralInformation);
                moduleGeneralInformation.setResponsibleTeacher(user);
                userRepository.save(user);
            } else {
                throw new BadRequestException("User with ID :" + moduleGeneralInformation.getModule().getId() + " does not exist");
            }
        }

        if(Objects.nonNull(moduleGeneralInformation.getAcademicUserSignatures()) && !moduleGeneralInformation.getAcademicUserSignatures().isEmpty()) {
            moduleGeneralInformation.setAcademicUserSignatures(moduleGeneralInformation.getAcademicUserSignatures().stream().map(academicUserSignature -> {
                Optional<UserSignature> optionalUserSignature = userSignatureRepository.findById(academicUserSignature.getId());
                if (optionalUserSignature.isPresent()) {
                    UserSignature userSignature = optionalUserSignature.get();
                    userSignature.getModulesInManagement().add(moduleGeneralInformation);
                    userSignatureRepository.save(userSignature);
                    return userSignature;
                } else {
                    throw new BadRequestException("UserSignature general information with ID :" + academicUserSignature.getId() + " does not exist");
                }
            }).collect(Collectors.toList()));
        }

            if (Objects.nonNull(moduleGeneralInformation.getAcademicTeachers()) && !moduleGeneralInformation.getAcademicTeachers().isEmpty()) {
                moduleGeneralInformation.setAcademicTeachers(moduleGeneralInformation.getAcademicTeachers().stream().map(academicTeacher -> {
                    Optional<User> optionalUser = userRepository.findById(academicTeacher.getId());
                    if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        user.getModulesInManagement().add(moduleGeneralInformation);
                        userRepository.save(user);
                        return user;
                    } else {
                        throw new BadRequestException("UserSignature general information with ID :" + academicTeacher.getId() + " does not exist");
                    }
                }).collect(Collectors.toSet()));
            }
    }

    @Transactional
    public ModuleGeneralInformation save(ModuleGeneralInformation moduleGeneralInformation) {
        return moduleGeneralInformationRepository.save(moduleGeneralInformation);
    }

    @Transactional
    public ModuleGeneralInformationDTO save(ModuleGeneralInformationDTO moduleGeneralInformationDTO) {
        ModuleGeneralInformation moduleGeneralInformation = this.moduleGeneralInformationMapper.toEntity(moduleGeneralInformationDTO);
        this.fetchRelations(moduleGeneralInformation);
        moduleGeneralInformation = moduleGeneralInformationRepository.save(moduleGeneralInformation);
        return this.moduleGeneralInformationMapper.toDto(moduleGeneralInformation);
    }

    @Transactional(readOnly = true)
    public Page<ModuleGeneralInformationDTO> findAll(Pageable pageable) {
        return moduleGeneralInformationRepository.findAll(pageable)
                .map(this.moduleGeneralInformationMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ModuleGeneralInformationDTO> findAll() {
        return moduleGeneralInformationRepository.findAll()
                .stream()
                .map(this.moduleGeneralInformationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModuleGeneralInformationDTO> findAllWhereModuleIsNull() {
        return StreamSupport
                .stream(moduleGeneralInformationRepository.findAll().spliterator(), false)
                .filter(moduleGeneralInformation -> moduleGeneralInformation.getModule() == null)
                .map(this.moduleGeneralInformationMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<ModuleGeneralInformationDTO> findOne(Long id) {
        return moduleGeneralInformationRepository.findById(id)
                .map(this.moduleGeneralInformationMapper::toDto);
    }

    public void delete(Long id) {
        moduleGeneralInformationRepository.deleteById(id);
    }
}
