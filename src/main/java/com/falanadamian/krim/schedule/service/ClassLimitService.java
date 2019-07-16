package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.model.*;
import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.mapper.*;
import com.falanadamian.krim.schedule.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassLimitService {

    private final ClassLimitRepository classLimitRepository;
    private final ClassLimitMapper classLimitMapper;

    public ClassLimitService(ClassLimitRepository classLimitRepository, ClassLimitMapper classLimitMapper) {
        this.classLimitRepository = classLimitRepository;
        this.classLimitMapper = classLimitMapper;
    }

    public ClassLimitDTO save(ClassLimitDTO classLimitDTO) {
        ClassLimit classLimit = classLimitMapper.toEntity(classLimitDTO);
        classLimit = classLimitRepository.save(classLimit);
        return classLimitMapper.toDto(classLimit);
    }

    @Transactional(readOnly = true)
    public List<ClassLimitDTO> findAll() {
        return classLimitRepository
                .findAll()
                .stream()
                .map(classLimitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ClassLimitDTO> findAll(Pageable pageable) {
        return classLimitRepository
                .findAll(pageable)
                .map(classLimitMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ClassLimitDTO> findOne(Long id) {
        return classLimitRepository
                .findById(id)
                .map(classLimitMapper::toDto);
    }

    public void delete(Long id) {
        classLimitRepository.deleteById(id);
    }
}
