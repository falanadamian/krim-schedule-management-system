package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ErrandDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.ErrandService;
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
@RequestMapping("/krim/errands")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ErrandController {

    private static final String ENTITY_NAME = "ERRAND";
    private static final String API_URI = "/krim/errands/";

    private final ErrandService errandService;

    public ErrandController(ErrandService errandService) {
        this.errandService = errandService;
    }

    @PostMapping()
    public ResponseEntity<ErrandDTO> createErrand(@Valid @RequestBody ErrandDTO errandDTO) throws URISyntaxException {
        if (errandDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        ErrandDTO result = errandService.save(errandDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<ErrandDTO> updateErrand(@Valid @RequestBody ErrandDTO errandDTO) {
        if (errandDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        ErrandDTO result = errandService.save(errandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, errandDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<ErrandDTO>> getAllErrands() {
        List<ErrandDTO> errandList = errandService.findAll();
        return ResponseEntity
            .ok()
            .body(errandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrandDTO> getErrand(@PathVariable Long id) {
        Optional<ErrandDTO> errandDTO = errandService.findOne(id);
        if (!errandDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(errandDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteErrand(@PathVariable Long id) {
        errandService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/module")
    public ResponseEntity<ErrandDTO> getErrandByCode(@RequestParam String code) {

        Optional<ErrandDTO> errandDTO = errandService.findOneByModuleCode(code);
        if (!errandDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for module code: " + code + " not found");
        return ResponseEntity
                .ok()
                .body(errandDTO.get());
    }

    @PostMapping("/ids")
    public ResponseEntity<List<ErrandDTO>> getAllModulesByIds(@RequestBody List<Long> ids) {
        Optional<List<ErrandDTO>> modules = errandService.findAllByIdList(ids);

        if(!modules.isPresent()) {
            throw new NoResultException("Brak wyników dla żądanej listy");
        }

        return ResponseEntity
                .ok()
                .body(modules.get());
    }
}
