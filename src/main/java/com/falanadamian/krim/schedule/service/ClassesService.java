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
public class ClassesService {

    private final ClassesRepository classesRepository;
    private final ClassesMapper classesMapper;

    public ClassesService(ClassesRepository classesRepository, ClassesMapper classesMapper) {
        this.classesRepository = classesRepository;
        this.classesMapper = classesMapper;
    }

    public ClassesDTO save(ClassesDTO classesDTO) {
        Classes classes = classesMapper.toEntity(classesDTO);
        classes = classesRepository.save(classes);
        return classesMapper.toDto(classes);
    }

    @Transactional(readOnly = true)
    public List<ClassesDTO> findAll() {
        return classesRepository
                .findAll()
                .stream()
                .map(classesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ClassesDTO> findAll(Pageable pageable) {
        return classesRepository
                .findAll(pageable)
                .map(classesMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ClassesDTO> findOne(Long id) {
        return classesRepository
                .findById(id)
                .map(classesMapper::toDto);
    }

    public void delete(Long id) {
        classesRepository.deleteById(id);
    }
}
