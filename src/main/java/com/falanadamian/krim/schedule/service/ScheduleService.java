package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.ScheduleDTO;
import com.falanadamian.krim.schedule.domain.mapper.ScheduleMapper;
import com.falanadamian.krim.schedule.domain.model.Module;
import com.falanadamian.krim.schedule.domain.model.Schedule;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.repository.ModuleRepository;
import com.falanadamian.krim.schedule.repository.ScheduleRepository;
import com.falanadamian.krim.schedule.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, UserRepository userRepository, ModuleRepository moduleRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
    }

    @Transactional
    protected void fetchRelations(Schedule schedule) {
        if (Objects.nonNull(schedule.getAssignee()) && Objects.nonNull(schedule.getAssignee().getId())) {
            Optional<User> optionalUser = userRepository.findById(schedule.getAssignee().getId());
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.getSchedulesAssignedTo().add(schedule);
                schedule.setAssignee(userRepository.save(user));
            }
        }
        if (Objects.nonNull(schedule.getAssignor()) && Objects.nonNull(schedule.getAssignor().getId())) {
            Optional<User> optionalUser = userRepository.findById(schedule.getAssignor().getId());
            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.getSchedulesAssignedBy().add(schedule);
                schedule.setAssignor(userRepository.save(user));
            }
        }
        if (Objects.nonNull(schedule.getModule()) && Objects.nonNull(schedule.getModule().getId())) {
            Optional<Module> optionalModule = moduleRepository.findById(schedule.getModule().getId());
            if(optionalModule.isPresent()) {
                Module module = optionalModule.get();
                module.getSchedules().add(schedule);
                schedule.setModule(moduleRepository.save(module));
            }
        }
    }

    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        this.fetchRelations(schedule);
        schedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleDTO> findAll() {
        return scheduleRepository
                .findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScheduleDTO> findAllByAssigneeUsername(String username) {
        return scheduleRepository
                .findAllByAssigneeUsername(username)
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ScheduleDTO> findAll(Pageable pageable) {
        return scheduleRepository
                .findAll(pageable)
                .map(scheduleMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<ScheduleDTO> findOne(Long id) {
        return scheduleRepository
                .findById(id)
                .map(scheduleMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<List<ScheduleDTO>> findAllByIdList(List<Long> ids) {
        return scheduleRepository.findAllByIdIn(ids)
                .map(this.scheduleMapper::toDto);
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
