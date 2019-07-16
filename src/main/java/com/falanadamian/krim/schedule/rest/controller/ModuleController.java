package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ModuleDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.security.SecurityHandler;
import com.falanadamian.krim.schedule.service.ModuleService;
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
@RequestMapping("/krim/modules")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ModuleController {

    private static final String ENTITY_NAME = "MODULE";
    private static final String API_URI = "/krim/modules/";

    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping()
    public ResponseEntity<ModuleDTO> createModule(@RequestBody ModuleDTO moduleDTO) throws URISyntaxException {
        if (moduleDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ModuleDTO result = moduleService.save(moduleDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ModuleDTO> updateModule(@RequestBody ModuleDTO moduleDTO) {
        if (moduleDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ModuleDTO result = moduleService.save(moduleDTO);
        return ResponseEntity.ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, moduleDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ModuleDTO>> getAllModules() {
        List<ModuleDTO> moduleList = moduleService.findAll();
        return ResponseEntity
            .ok()
            .body(moduleList);
    }

    @PostMapping("/all")
    public ResponseEntity<List<ModuleDTO>> getAllModulesByIds(@RequestBody List<Long> ids) {
        Optional<List<ModuleDTO>> modules = moduleService.findAllByIdList(ids);

        if(!modules.isPresent()) {
            throw new NoResultException("Brak wyników dla żądanej listy");
        }

        return ResponseEntity
                .ok()
                .body(modules.get());
    }

    @GetMapping("/me")
    public ResponseEntity<List<ModuleDTO>> getAllCurrentlyLoggedInUserModules() {
        List<ModuleDTO> moduleList = moduleService.findAllByResponsibleTeacherUsername(SecurityHandler.getCurrentUserUsername().get());
        return ResponseEntity
                .ok()
                .body(moduleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDTO> getModule(@PathVariable Long id) {
        Optional<ModuleDTO> moduleDTO = moduleService.findOne(id);
        if (!moduleDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(moduleDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
