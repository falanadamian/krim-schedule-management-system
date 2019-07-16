package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.UserConfigDTO;
import com.falanadamian.krim.schedule.domain.mapper.UserConfigMapper;
import com.falanadamian.krim.schedule.domain.model.UserConfig;
import com.falanadamian.krim.schedule.domain.model.enumeration.Position;
import com.falanadamian.krim.schedule.repository.UserConfigRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserConfigService {

    private final UserConfigRepository userConfigRepository;
    private final UserConfigMapper userConfigMapper;

    public UserConfigService(UserConfigRepository userConfigRepository, UserConfigMapper userConfigMapper) {
        this.userConfigRepository = userConfigRepository;
        this.userConfigMapper = userConfigMapper;
    }

    public UserConfigDTO save(UserConfigDTO userConfigDTO) {
        UserConfig userConfig = userConfigMapper.toEntity(userConfigDTO);
        userConfig = userConfigRepository.save(userConfig);
        return userConfigMapper.toDto(userConfig);
    }

    @Transactional(readOnly = true)
    public List<UserConfigDTO> findAll() {
        return userConfigRepository
                .findAll()
                .stream()
                .map(userConfigMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<UserConfigDTO> findAll(Pageable pageable) {
        return userConfigRepository
                .findAll(pageable)
                .map(userConfigMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<UserConfigDTO> findOne(Position position) {
        return userConfigRepository
                .findById(position)
                .map(userConfigMapper::toDto);
    }

    public void delete(Position position) {
        userConfigRepository.deleteById(position);
    }
}
