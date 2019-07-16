package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.LimitConfigDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.LimitConfigService;
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
@RequestMapping("/krim/limit-configs")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class LimitConfigController {

    private static final String ENTITY_NAME = "LIMIT_CONFIG";
    private static final String API_URI = "/krim/limit-configs/";

    private final LimitConfigService limitConfigService;

    public LimitConfigController(LimitConfigService limitConfigService) {
        this.limitConfigService = limitConfigService;
    }

    @PostMapping()
    public ResponseEntity<LimitConfigDTO> createLimitConfig(@Valid @RequestBody LimitConfigDTO limitConfigDTO) throws URISyntaxException {
        if (limitConfigDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        LimitConfigDTO result = limitConfigService.save(limitConfigDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<LimitConfigDTO> updateLimitConfig(@Valid @RequestBody LimitConfigDTO limitConfigDTO) {
        if (limitConfigDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        LimitConfigDTO result = limitConfigService.save(limitConfigDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, limitConfigDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<LimitConfigDTO>> getAllLimitConfigs() {
        List<LimitConfigDTO> limitConfigList = limitConfigService.findAll();
        return ResponseEntity
            .ok()
            .body(limitConfigList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LimitConfigDTO> getLimitConfig(@PathVariable Long id) {
        Optional<LimitConfigDTO> limitConfigDTO = limitConfigService.findOne(id);
        if (!limitConfigDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(limitConfigDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimitConfig(@PathVariable Long id) {
        limitConfigService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
