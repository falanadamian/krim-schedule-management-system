package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.ModuleDTO;
import com.falanadamian.krim.schedule.domain.mapper.ModuleMapper;
import com.falanadamian.krim.schedule.domain.model.Module;
import com.falanadamian.krim.schedule.repository.ModuleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    public ModuleService(ModuleRepository moduleRepository, ModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.moduleMapper = moduleMapper;
    }

    public Module saveModule(Module module) {
        return moduleRepository.save(module);
    }

    public ModuleDTO save(Module module) {
        module = moduleRepository.save(module);
        return this.moduleMapper.toDto(module);
    }

    public ModuleDTO save(ModuleDTO moduleDTO) {
        Module module = this.moduleMapper.toEntity(moduleDTO);
        module = moduleRepository.save(module);
        return this.moduleMapper.toDto(module);
    }

    @Transactional(readOnly = true)
    public List<ModuleDTO> findAll() {
        return moduleRepository
                .findAll()
                .stream()
                .map(this.moduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ModuleDTO> findAll(Pageable pageable) {
        return moduleRepository
                .findAll(pageable)
                .map(this.moduleMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ModuleDTO> findOne(Long id) {
        return moduleRepository
                .findById(id)
                .map(this.moduleMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<List<ModuleDTO>> findAllByIdList(List<Long> ids) {
        return moduleRepository.findAllByIdIn(ids)
                .map(this.moduleMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ModuleDTO> findAllByResponsibleTeacherUsername(String username) {
        return moduleRepository.findAllByModuleGeneralInformation_ResponsibleTeacher_Username(username)
                .stream()
                .map(this.moduleMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        moduleRepository.deleteById(id);
    }

    public boolean existsByCode(String code) {
        return moduleRepository.existsByCode(code);
    }
}
