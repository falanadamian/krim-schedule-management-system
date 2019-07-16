package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.UserDTO;
import com.falanadamian.krim.schedule.domain.mapper.UserMapper;
import com.falanadamian.krim.schedule.domain.model.User;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.exception.EmailAlreadyInUseException;
import com.falanadamian.krim.schedule.exception.UsernameAlreadyInUseException;
import com.falanadamian.krim.schedule.repository.UserRepository;
import com.falanadamian.krim.schedule.service.MailService;
import com.falanadamian.krim.schedule.service.UserService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("krim/users")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class UserController {

    private static final String ENTITY_NAME = "USER";
    private static final String API_URI = "/krim/users/";

    private final UserMapper userMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;


    public UserController(UserService userService, UserRepository userRepository, UserMapper userMapper, MailService mailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailService = mailService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {

        if (userDTO.getId() != null) {
            throw new BadRequestException("When creating new user ID should not be specified");
        } else if (userRepository.findOneByUsername(userDTO.getUsername().toLowerCase()).isPresent()) {
            throw new UsernameAlreadyInUseException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException();
        } else {
            User user = userService.createUser(userDTO);
            mailService.sendAccountCreatedEmail(user);

            return ResponseEntity
                    .created(new URI(API_URI + user.getId()))
                    .headers(HeaderGenerator.generateCreationHeader(ENTITY_NAME, user.getId().toString()))
                    .body(userMapper.toDto(user));
        }
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyInUseException();
        }
        existingUser = userRepository.findOneByUsername(userDTO.getUsername().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new UsernameAlreadyInUseException();
        }
        Optional<UserDTO> user = userService.updateUser(userDTO);

        return ResponseEntity
                .created(new URI(API_URI + user.get().getId()))
                .headers(HeaderGenerator.generateUpdateHeader(ENTITY_NAME, user.get().getId().toString()))
                .body(user.get());
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        final List<UserDTO> userList = userService.findAll();
        return ResponseEntity
                .ok()
                .body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        final Optional<UserDTO> user = userService.findOne(id);
        if(!user.isPresent())
            throw new BadRequestException("User with id {{id}} not found.");
        return ResponseEntity
                .ok()
                .body(user.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        final Optional<UserDTO> user = userService.findOne(id);
        if(!user.isPresent())
            throw new BadRequestException("User with id {{id}} not found.");

        userService.delete(id);
        return ResponseEntity
                .ok()
                .headers(HeaderGenerator.generateDeletionHeader(ENTITY_NAME, id.toString()))
                .build();
    }

    @PostMapping("/ids")
    public ResponseEntity<List<UserDTO>> getAllModulesByIds(@RequestBody List<Long> ids) {
        Optional<List<UserDTO>> users = userService.findAllByIdList(ids);

        if(!users.isPresent()) {
            return ResponseEntity
                    .ok()
                    .body(new ArrayList<>());
        }

        return ResponseEntity
                .ok()
                .body(users.get());
    }

}
