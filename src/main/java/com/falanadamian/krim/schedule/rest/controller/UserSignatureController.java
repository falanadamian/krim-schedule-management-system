package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.UserSignatureDTO;
import com.falanadamian.krim.schedule.domain.model.enumeration.Position;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.UserSignatureService;
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
@RequestMapping("/krim/user-signatures")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class UserSignatureController {

    private static final String ENTITY_NAME = "USER_SIGNATURE";
    private static final String API_URI = "/krim/user-signatures/";

    private final UserSignatureService userSignatureService;

    public UserSignatureController(UserSignatureService userSignatureService) {
        this.userSignatureService = userSignatureService;
    }

    @PostMapping()
    public ResponseEntity<UserSignatureDTO> createUserSignature(@Valid @RequestBody UserSignatureDTO userSignatureDTO) throws URISyntaxException {
        UserSignatureDTO result = userSignatureService.save(userSignatureDTO);
        return ResponseEntity
                .created(new URI(API_URI + result.getId()))
                .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping()
    public ResponseEntity<UserSignatureDTO> updateUserSignature(@Valid @RequestBody UserSignatureDTO userSignatureDTO) {
        if (userSignatureDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        UserSignatureDTO result = userSignatureService.save(userSignatureDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, userSignatureDTO.getId().toString()))
                .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<UserSignatureDTO>> getAllUserSignatures() {
        List<UserSignatureDTO> userSignatures = userSignatureService.findAll();
        return ResponseEntity
                .ok()
                .body(userSignatures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSignatureDTO> getUserSignature(@PathVariable Long id) {
        Optional<UserSignatureDTO> userSignatureDTO = userSignatureService.findOne(id);
        if (!userSignatureDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + id + " not found");
        return ResponseEntity
                .ok()
                .body(userSignatureDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSignature(@PathVariable Long id) {
        userSignatureService.delete(id);
        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
                .build();
    }
}
