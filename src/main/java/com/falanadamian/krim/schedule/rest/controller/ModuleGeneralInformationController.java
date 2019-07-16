package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ModuleGeneralInformationDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.ModuleGeneralInformationService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/module-general-informations")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ModuleGeneralInformationController {

    private static final String ENTITY_NAME = "MODULE_GENERAL_INFORMATION";
    private static final String API_URI = "/krim/module-general-informations/";

    private final ModuleGeneralInformationService moduleGeneralInformationService;

    public ModuleGeneralInformationController(ModuleGeneralInformationService moduleGeneralInformationService) {
        this.moduleGeneralInformationService = moduleGeneralInformationService;
    }

    @PostMapping()
    public ResponseEntity<ModuleGeneralInformationDTO> createModuleGeneralInformation(@Valid @RequestBody ModuleGeneralInformationDTO moduleGeneralInformationDTO) throws URISyntaxException {
        if (moduleGeneralInformationDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ModuleGeneralInformationDTO result = moduleGeneralInformationService.save(moduleGeneralInformationDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ModuleGeneralInformationDTO> updateModuleGeneralInformation(@Valid @RequestBody ModuleGeneralInformationDTO moduleGeneralInformationDTO) {
        if (moduleGeneralInformationDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ModuleGeneralInformationDTO result = moduleGeneralInformationService.save(moduleGeneralInformationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, moduleGeneralInformationDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ModuleGeneralInformationDTO>> getAllModuleGeneralInformations() {
        List<ModuleGeneralInformationDTO> moduleGeneralInformations = moduleGeneralInformationService.findAll();
        return ResponseEntity
                .ok()
                .body(moduleGeneralInformations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleGeneralInformationDTO> getModuleGeneralInformation(@PathVariable Long id) {
        Optional<ModuleGeneralInformationDTO> moduleGeneralInformationDTO = moduleGeneralInformationService.findOne(id);
        if (!moduleGeneralInformationDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(moduleGeneralInformationDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModuleGeneralInformation(@PathVariable Long id) {
        moduleGeneralInformationService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
