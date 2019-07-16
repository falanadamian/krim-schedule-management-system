package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.RoleDTO;
import com.falanadamian.krim.schedule.domain.dto.UserDTO;
import com.falanadamian.krim.schedule.domain.mapper.StudyInfoMapper;
import com.falanadamian.krim.schedule.domain.mapper.UserMapper;
import com.falanadamian.krim.schedule.domain.model.*;
import com.falanadamian.krim.schedule.domain.model.enumeration.RoleType;
import com.falanadamian.krim.schedule.exception.*;
import com.falanadamian.krim.schedule.repository.*;
import com.falanadamian.krim.schedule.security.SecurityHandler;
import com.falanadamian.krim.schedule.security.SecurityRole;
import com.falanadamian.krim.schedule.util.RandomGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserConfigRepository userConfigRepository;
    private final UserMapper userMapper;
    private final ModuleGeneralInformationRepository moduleGeneralInformationRepository;
    private final StudyInfoRepository studyInfoRepository;
    private final StudyInfoMapper studyInfoMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       UserConfigRepository userConfigRepository,
                       UserMapper userMapper,
                       ModuleGeneralInformationRepository moduleGeneralInformationRepository,
                       StudyInfoRepository studyInfoRepository,
                       StudyInfoMapper studyInfoMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userConfigRepository = userConfigRepository;
        this.userMapper = userMapper;
        this.moduleGeneralInformationRepository = moduleGeneralInformationRepository;
        this.studyInfoRepository = studyInfoRepository;
        this.studyInfoMapper = studyInfoMapper;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    public List<UserDTO> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(userMapper::toDto);
    }

    @Transactional
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public Optional<User> findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    public Optional<UserDTO> findOneByEmailIgnoreCase(String email) {
        return userRepository.findOneByEmailIgnoreCase(email).map(userMapper::toDto);
    }

    @Transactional
    public Optional<List<UserDTO>> findAllByIdList(List<Long> ids) {
        return userRepository.findAllByIdIn(ids)
                .map(this.userMapper::toDto);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> activateRegistration(String key) {
        return userRepository.findOneByActivationKey(key).map(user -> {
            user.setActivated(true);
            user.setActivationKey(null);
            return user;
        });
    }

    public Optional<User> setPassword(String newPassword, String key) {
        return userRepository.findOneByResetKey(key)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    user.setResetKey(null);
                    user.setResetDate(null);
                    return user;
                });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
                .filter(User::isActivated)
                .map(user -> {
                    user.setResetKey(RandomGenerator.generateResetKey());
                    user.setResetDate(Instant.now());
                    return user;
                });
    }

    public User registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByUsername(userDTO.getUsername().toLowerCase()).ifPresent(existingUser -> {
            throw new UsernameAlreadyInUseException();
        });
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            throw new EmailAlreadyInUseException();
        });
        User user = new User();
        String encodedPassword = passwordEncoder.encode(password);

        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setPassword(encodedPassword);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setIdentity(userDTO.getIdentity());
        user.setDegree(userDTO.getDegree());
        Optional<UserConfig> userConfig = userConfigRepository.findById(userDTO.getPosition());

        if (userConfig.isPresent()) {
            user.setPosition(userConfig.get());
        } else {
            throw new RoleNotFoundException();
        }

        user.setActivated(false);
        user.setActivationKey(RandomGenerator.generateActivationKey());
        Set<Role> roles = new HashSet<>();
        Optional<Role> role = roleRepository.findById(RoleType.of(SecurityRole.USER));
        if (role.isPresent()) {
            roles.add(role.get());
        } else {
            throw new RoleNotFoundException();
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setActivated(true);
        user.setNote(userDTO.getNote());
        user.setReduction(userDTO.getReduction());
        user.setIdentity(userDTO.getIdentity());
        user.setDegree(userDTO.getDegree());
        user.setPosition(userConfigRepository.findById(userDTO.getPosition()).get());
        Set<Role> roles = user.getRoles();
        roles.clear();
        userDTO.getRoles().stream()
                .map(RoleDTO::getRoleType)
                .map(RoleType::of)
                .map(roleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(roles::add);

        String encodedPassword = passwordEncoder.encode(RandomGenerator.generatePassword());
        user.setPassword(encodedPassword);
        user.setResetKey(RandomGenerator.generateResetKey());
        user.setResetDate(Instant.now());

        userRepository.save(user);
        userRepository.flush();

        userDTO.getModulesInChargeovesIds().forEach( moduleInChargeoverId -> {
            ModuleGeneralInformation moduleGeneralInformation = moduleGeneralInformationRepository.getById(moduleInChargeoverId);
            if(moduleGeneralInformation.getResponsibleTeacher() != null && !moduleGeneralInformation.getResponsibleTeacher().getId().equals(user.getId())) {
                throw new BadRequestException("Module " + moduleGeneralInformation.getCode() + " already has got responsible teacher: " + moduleGeneralInformation.getResponsibleTeacher().getUsername());
            }
            moduleGeneralInformation.setResponsibleTeacher(user);
        });
        userDTO.getModulesInManagementIds().forEach(moduleInManagementId -> {
            ModuleGeneralInformation moduleGeneralInformation = moduleGeneralInformationRepository.getById(moduleInManagementId);
            user.getModulesInManagement().add(moduleGeneralInformation);
        });

        if(userDTO.getStudyInfo().getId() != null) {
            StudyInfo studyInfo = studyInfoRepository.getOne(userDTO.getStudyInfo().getId());
            user.setStudyInfo(studyInfo);
        }
        if(userDTO.getStudyInfo().getPatronId() != null) {
            StudyInfo studyInfo = studyInfoRepository.save(studyInfoMapper.toEntity(userDTO.getStudyInfo()));
            user.setStudyInfo(studyInfo);
        }

        userRepository.flush();
        moduleGeneralInformationRepository.flush();

        return userRepository.save(user);
    }

    public void updateUser(String firstName, String lastName, String email) {

        SecurityHandler.getCurrentUserUsername()
                .flatMap(userRepository::findOneByUsername)
                .ifPresent(user -> {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                });
    }

    public Optional<UserDTO> updateUser(UserDTO userDTO) {
        return Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    user.setUsername(userDTO.getUsername().toLowerCase());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setEmail(userDTO.getEmail().toLowerCase());
//                    user.setActivated(userDTO.isActivated());
                    user.setNote(userDTO.getNote());
                    user.setReduction(userDTO.getReduction());
                    user.setIdentity(userDTO.getIdentity());
                    user.setDegree(userDTO.getDegree());
                    user.setPosition(userConfigRepository.findById(userDTO.getPosition()).get());
                    Set<Role> roles = user.getRoles();
                    roles.clear();
                    userDTO.getRoles().stream()
                            .map(RoleDTO::getRoleType)
                            .map(RoleType::of)
                            .map(roleRepository::findById)
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .forEach(roles::add);
                    userDTO.getModulesInChargeovesIds().forEach( moduleInChargeoverId -> {
                        ModuleGeneralInformation moduleGeneralInformation = moduleGeneralInformationRepository.getById(moduleInChargeoverId);
                        if(moduleGeneralInformation.getResponsibleTeacher() != null && !moduleGeneralInformation.getResponsibleTeacher().getId().equals(user.getId())) {
                            throw new BadRequestException("Module " + moduleGeneralInformation.getCode() + " already has got responsible teacher: " + moduleGeneralInformation.getResponsibleTeacher().getUsername());
                        }
                        moduleGeneralInformation.setResponsibleTeacher(user);
                    });
                    userDTO.getModulesInManagementIds().forEach(moduleInManagementId -> {
                        ModuleGeneralInformation moduleGeneralInformation = moduleGeneralInformationRepository.getById(moduleInManagementId);
                        user.getModulesInManagement().add(moduleGeneralInformation);
                    });

                    if(userDTO.getStudyInfo().getId() != null) {
                        StudyInfo studyInfo = studyInfoRepository.getOne(userDTO.getStudyInfo().getId());
                        user.setStudyInfo(studyInfo);
                    }
                    if(userDTO.getStudyInfo().getPatronId() != null) {
                        StudyInfo studyInfo = studyInfoRepository.save(studyInfoMapper.toEntity(userDTO.getStudyInfo()));
                        user.setStudyInfo(studyInfo);
                    }

                    userRepository.flush();
                    moduleGeneralInformationRepository.flush();
                    return user;
                })
                .map(UserDTO::new);
    }

    public void deleteById(Long id) {
        userRepository.findById(id)
                .ifPresent(userRepository::delete);
    }

    public void deleteByEmail(String email) {
        userRepository.findOneByEmailIgnoreCase(email)
                .ifPresent(userRepository::delete);
    }

    public void deleteByUsername(String username) {
        userRepository.findOneByUsername(username)
                .ifPresent(userRepository::delete);
    }

    public void changePassword(String currentPassword, String newPassword) {
        SecurityHandler.getCurrentUserUsername().flatMap(userRepository::findOneByUsername)
                .ifPresent(user -> {
                    String currentEncodedPassword = user.getPassword();
                    if (!passwordEncoder.matches(currentPassword, currentEncodedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String newEncodedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(newEncodedPassword);
                });
    }

    @Transactional
    public Optional<User> getUserWithRoles() {
        return SecurityHandler.getCurrentUserUsername().flatMap(userRepository::findOneWithRolesByUsername);
    }

}
