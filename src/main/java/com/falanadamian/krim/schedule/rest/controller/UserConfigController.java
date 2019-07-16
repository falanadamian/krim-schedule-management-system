package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.UserConfigDTO;
import com.falanadamian.krim.schedule.domain.model.enumeration.Position;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.HttpNotFoundException;
import com.falanadamian.krim.schedule.service.UserConfigService;
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
@RequestMapping("/krim/user-configs")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class UserConfigController {

    private static final String ENTITY_NAME = "USER_CONFIG";
    private static final String API_URI = "/krim/user-configs/";

    private final UserConfigService userConfigService;

    public UserConfigController(UserConfigService userConfigService) {
        this.userConfigService = userConfigService;
    }

    @PostMapping()
    public ResponseEntity<UserConfigDTO> createUserConfig(@Valid @RequestBody UserConfigDTO userConfigDTO) throws URISyntaxException {
        UserConfigDTO result = userConfigService.save(userConfigDTO);
        return ResponseEntity
            .created(new URI(API_URI + result.getPosition()))
            .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, result.getPosition().toString()))
            .body(result);
    }

    @PutMapping()
    public ResponseEntity<UserConfigDTO> updateUserConfig(@Valid @RequestBody UserConfigDTO userConfigDTO) {
        if (userConfigDTO.getPosition() == null) {
            throw new BadRequestException("Invalid id");
        }
        UserConfigDTO result = userConfigService.save(userConfigDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, userConfigDTO.getPosition().toString()))
            .body(result);
    }

    @GetMapping()
    public ResponseEntity<List<UserConfigDTO>> getAllUserConfigs() {
        List<UserConfigDTO> userConfigList = userConfigService.findAll();
        return ResponseEntity
            .ok()
            .body(userConfigList);
    }

    @GetMapping("/{position}")
    public ResponseEntity<UserConfigDTO> getUserConfig(@PathVariable Position position) {
        Optional<UserConfigDTO> userConfigDTO = userConfigService.findOne(position);
        if (!userConfigDTO.isPresent())
            throw new HttpNotFoundException(ENTITY_NAME + "for id: " + position + " not found");
        return ResponseEntity
            .ok()
            .body(userConfigDTO.get());
    }

    @DeleteMapping("/{position}")
    public ResponseEntity<Void> deleteUserConfig(@PathVariable Position position) {
        userConfigService.delete(position);
        return ResponseEntity
            .ok()
            .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, position.toString()))
            .build();
    }
}
