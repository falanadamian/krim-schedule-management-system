package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.model.*;
import com.falanadamian.krim.schedule.domain.dto.*;
import com.falanadamian.krim.schedule.domain.mapper.*;
import com.falanadamian.krim.schedule.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ErrandService {

    private final ErrandRepository errandRepository;
    private final ErrandMapper errandMapper;

    public ErrandService(ErrandRepository errandRepository, ErrandMapper errandMapper) {
        this.errandRepository = errandRepository;
        this.errandMapper = errandMapper;
    }

    public ErrandDTO save(ErrandDTO errandDTO) {
        Errand errand = errandMapper.toEntity(errandDTO);
        if(errand.getModule().getId() == null) {
            errand.module(null);
        }/*
        if(errand.getSchedule().getId() == null) {
            errand.schedule(null);
        }*/
        errand = errandRepository.save(errand);
        return errandMapper.toDto(errand);
    }

    @Transactional(readOnly = true)
    public List<ErrandDTO> findAll() {
        return errandRepository
                .findAll()
                .stream()
                .map(errandMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ErrandDTO> findAll(Pageable pageable) {
        return errandRepository
                .findAll(pageable)
                .map(errandMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ErrandDTO> findOne(Long id) {
        return errandRepository
                .findById(id)
                .map(errandMapper::toDto);
    }

    public void delete(Long id) {
        errandRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ErrandDTO> findOneByModuleCode(String code) {
        return errandRepository
                .getByModuleCode(code)
                .map(errandMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<List<ErrandDTO>> findAllByIdList(List<Long> ids) {
        return errandRepository.findAllByIdIn(ids)
                .map(this.errandMapper::toDto);
    }
}
