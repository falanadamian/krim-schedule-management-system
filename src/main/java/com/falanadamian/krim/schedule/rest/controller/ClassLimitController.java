package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ClassLimitDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.ClassLimitService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/class-limits")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ClassLimitController {

    private static final String ENTITY_NAME = "CLASS_LIMIT";
    private static final String API_URI = "/krim/class-limits/";


    private final ClassLimitService classLimitService;

    public ClassLimitController(ClassLimitService classLimitService) {
        this.classLimitService = classLimitService;
    }

    @PostMapping()
    public ResponseEntity<ClassLimitDTO> createClassLimit(@RequestBody ClassLimitDTO classLimitDTO) throws URISyntaxException {
        if (classLimitDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ClassLimitDTO result = classLimitService.save(classLimitDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ClassLimitDTO> updateClassLimit(@RequestBody ClassLimitDTO classLimitDTO) {
        if (classLimitDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ClassLimitDTO result = classLimitService.save(classLimitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, classLimitDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ClassLimitDTO>> getAllClassLimits() {
        List<ClassLimitDTO> classLimitList= classLimitService.findAll();
        return ResponseEntity
            .ok()
            .body(classLimitList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassLimitDTO> getClassLimit(@PathVariable Long id) {
        Optional<ClassLimitDTO> classLimitDTO = classLimitService.findOne(id);
        if (!classLimitDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(classLimitDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassLimit(@PathVariable Long id) {
        classLimitService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
