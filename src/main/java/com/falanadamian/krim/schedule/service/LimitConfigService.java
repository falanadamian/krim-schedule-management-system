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
public class LimitConfigService {

    private final LimitConfigRepository limitConfigRepository;
    private final LimitConfigMapper limitConfigMapper;

    public LimitConfigService(LimitConfigRepository limitConfigRepository, LimitConfigMapper limitConfigMapper) {
        this.limitConfigRepository = limitConfigRepository;
        this.limitConfigMapper = limitConfigMapper;
    }

    public LimitConfigDTO save(LimitConfigDTO limitConfigDTO) {
        LimitConfig limitConfig = limitConfigMapper.toEntity(limitConfigDTO);
        limitConfig = limitConfigRepository.save(limitConfig);
        return limitConfigMapper.toDto(limitConfig);
    }

    @Transactional(readOnly = true)
    public List<LimitConfigDTO> findAll() {
        return limitConfigRepository
                .findAll()
                .stream()
                .map(limitConfigMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<LimitConfigDTO> findAll(Pageable pageable) {
        return limitConfigRepository
                .findAll(pageable)
                .map(limitConfigMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<LimitConfigDTO> findOne(Long id) {
        return limitConfigRepository
                .findById(id)
                .map(limitConfigMapper::toDto);
    }

    public void delete(Long id) {
        limitConfigRepository.deleteById(id);
    }
}
