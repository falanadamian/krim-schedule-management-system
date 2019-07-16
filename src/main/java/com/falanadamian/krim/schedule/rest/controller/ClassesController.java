package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ClassesDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.ClassesService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/classes")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ClassesController {

    private static final String ENTITY_NAME = "CLASSES";
    private static final String API_URI = "/krim/classes/";

    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping()
    public ResponseEntity<ClassesDTO> createClasses(@Valid @RequestBody ClassesDTO classesDTO) throws URISyntaxException {
        if (classesDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ClassesDTO result = classesService.save(classesDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ClassesDTO> updateClasses(@Valid @RequestBody ClassesDTO classesDTO) {
        if (classesDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ClassesDTO result = classesService.save(classesDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, classesDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        List<ClassesDTO> classesList = classesService.findAll();
        return ResponseEntity
            .ok()
            .body(classesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassesDTO> getClasses(@PathVariable Long id) {
        Optional<ClassesDTO> classesDTO = classesService.findOne(id);
        if (!classesDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(classesDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasses(@PathVariable Long id) {
        classesService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
