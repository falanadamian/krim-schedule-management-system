package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.AdditionalHoursConfigDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.security.SecurityRole;
import com.falanadamian.krim.schedule.service.AdditionalHoursConfigService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/additional-hours-configs")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class AdditionalHoursConfigController {

    private static final String ENTITY_NAME = "ADDITIONAL_HOURS_CONFIG";
    private static final String API_URI = "/krim/additional-hours-configs/";

    private final AdditionalHoursConfigService additionalHoursConfigService;

    public AdditionalHoursConfigController(AdditionalHoursConfigService additionalHoursConfigService) {
        this.additionalHoursConfigService = additionalHoursConfigService;
    }

    @PostMapping()
    @PreAuthorize("hasRole(" + SecurityRole.USER + ")")
    public ResponseEntity<AdditionalHoursConfigDTO> createAdditionalHoursConfig(@Valid @RequestBody AdditionalHoursConfigDTO additionalHoursConfigDTO) throws URISyntaxException {
        if (additionalHoursConfigDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        AdditionalHoursConfigDTO result = additionalHoursConfigService.save(additionalHoursConfigDTO);
        return ResponseEntity
                .created(new URI(API_URI + result.getId()))
                .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping()
    @PreAuthorize("hasRole(" + SecurityRole.USER + ")")
    public ResponseEntity<AdditionalHoursConfigDTO> updateAdditionalHoursConfig(@Valid @RequestBody AdditionalHoursConfigDTO additionalHoursConfigDTO) {
        if (additionalHoursConfigDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        AdditionalHoursConfigDTO result = additionalHoursConfigService.save(additionalHoursConfigDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, additionalHoursConfigDTO.getId().toString()))
                .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<AdditionalHoursConfigDTO>> getAllAdditionalHoursConfigs() {
        List<AdditionalHoursConfigDTO> additionalHoursConfigList = additionalHoursConfigService.findAll();
        return ResponseEntity
                .ok()
                .body(additionalHoursConfigList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdditionalHoursConfigDTO> getAdditionalHoursConfig(@PathVariable Long id) {
        Optional<AdditionalHoursConfigDTO> additionalHoursConfigDTO = additionalHoursConfigService.findOne(id);
        if (!additionalHoursConfigDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
                .ok()
                .body(additionalHoursConfigDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdditionalHoursConfig(@PathVariable Long id) {
        additionalHoursConfigService.delete(id);
        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
                .build();
    }
}
