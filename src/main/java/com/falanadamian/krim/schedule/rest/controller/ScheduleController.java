package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ScheduleDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.security.SecurityHandler;
import com.falanadamian.krim.schedule.service.ScheduleService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/schedules")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ScheduleController {

    private static final String ENTITY_NAME = "SCHEDULE";
    private static final String API_URI = "/krim/schedules/";

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping()
    public ResponseEntity<ScheduleDTO> createSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) throws URISyntaxException {
        if (scheduleDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ScheduleDTO result = scheduleService.save(scheduleDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ScheduleDTO> updateSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        if (scheduleDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ScheduleDTO result = scheduleService.save(scheduleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, scheduleDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> scheduleList = scheduleService.findAll();
        return ResponseEntity
            .ok()
            .body(scheduleList);
    }

    @GetMapping("/me")
    public ResponseEntity<List<ScheduleDTO>> getAllUserSchedules() {

        List<ScheduleDTO> scheduleList = scheduleService.findAllByAssigneeUsername(SecurityHandler.getCurrentUserUsername().get());

        return ResponseEntity
                .ok()
                .body(scheduleList);
    }

    @PostMapping("/ids")
    public ResponseEntity<List<ScheduleDTO>> getAllModulesByIds(@RequestBody List<Long> ids) {
        Optional<List<ScheduleDTO>> modules = scheduleService.findAllByIdList(ids);

        if(!modules.isPresent()) {
            throw new NoResultException("Brak wyników dla żądanej listy");
        }

        return ResponseEntity
                .ok()
                .body(modules.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable Long id) {
        Optional<ScheduleDTO> scheduleDTO = scheduleService.findOne(id);
        if (!scheduleDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(scheduleDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
