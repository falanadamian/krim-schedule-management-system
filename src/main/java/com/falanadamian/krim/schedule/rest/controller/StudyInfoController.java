package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.StudyInfoDTO;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.StudyInfoService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/krim/study-infos")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class StudyInfoController {

    private static final String ENTITY_NAME = "STUDY_INFO";
    private static final String API_URI = "/krim/study-infos/";

    private final StudyInfoService studyInfoService;

    public StudyInfoController(StudyInfoService studyInfoService) {
        this.studyInfoService = studyInfoService;
    }

    @PostMapping()
    public ResponseEntity<StudyInfoDTO> createStudyInfo(@RequestBody StudyInfoDTO studyInfoDTO) throws URISyntaxException {
        if (studyInfoDTO.getId() != null) {
            throw new BadRequestException("When creating, " + ENTITY_NAME + " cannot possess an ID");
        }
        StudyInfoDTO result = studyInfoService.save(studyInfoDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getId()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<StudyInfoDTO> updateStudyInfo(@RequestBody StudyInfoDTO studyInfoDTO) {
        if (studyInfoDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        StudyInfoDTO result = studyInfoService.save(studyInfoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, studyInfoDTO.getId().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<StudyInfoDTO>> getAllStudyInfos(@RequestParam(required = false) String filter) {
        if ("user-is-null".equals(filter)) {
            return new ResponseEntity<>(studyInfoService.findAllWhereUserIsNull(),
                HttpStatus.OK);
        }
        List<StudyInfoDTO> studyInfoList = studyInfoService.findAll();
        return ResponseEntity
            .ok()
            .body(studyInfoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyInfoDTO> getStudyInfo(@PathVariable Long id) {
        Optional<StudyInfoDTO> studyInfoDTO = studyInfoService.findOne(id);
        if (!studyInfoDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
            .ok()
            .body(studyInfoDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyInfo(@PathVariable Long id) {
        studyInfoService.delete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
            .build();
    }
}
