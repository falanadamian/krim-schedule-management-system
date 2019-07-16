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
public class AdditionalHoursConfigService {

    private final AdditionalHoursConfigRepository additionalHoursConfigRepository;

    private final AdditionalHoursConfigMapper additionalHoursConfigMapper;

    public AdditionalHoursConfigService(AdditionalHoursConfigRepository additionalHoursConfigRepository, AdditionalHoursConfigMapper additionalHoursConfigMapper) {
        this.additionalHoursConfigRepository = additionalHoursConfigRepository;
        this.additionalHoursConfigMapper = additionalHoursConfigMapper;
    }

    public AdditionalHoursConfigDTO save(AdditionalHoursConfigDTO additionalHoursConfigDTO) {
        AdditionalHoursConfig additionalHoursConfig = additionalHoursConfigMapper.toEntity(additionalHoursConfigDTO);
        additionalHoursConfig = additionalHoursConfigRepository.save(additionalHoursConfig);
        return additionalHoursConfigMapper.toDto(additionalHoursConfig);
    }

    @Transactional(readOnly = true)
    public List<AdditionalHoursConfigDTO> findAll() {
        return additionalHoursConfigRepository
                .findAll()
                .stream()
                .map(additionalHoursConfigMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<AdditionalHoursConfigDTO> findAll(Pageable pageable) {
        return additionalHoursConfigRepository
                .findAll(pageable)
                .map(additionalHoursConfigMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<AdditionalHoursConfigDTO> findOne(Long id) {
        return additionalHoursConfigRepository
                .findById(id)
                .map(additionalHoursConfigMapper::toDto);
    }

    public void delete(Long id) {
        additionalHoursConfigRepository.deleteById(id);
    }
}
