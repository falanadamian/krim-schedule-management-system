package com.falanadamian.krim.schedule.rest.controller;

import com.falanadamian.krim.schedule.domain.dto.ModuleDTO;
import com.falanadamian.krim.schedule.domain.mapper.ModuleMapper;
import com.falanadamian.krim.schedule.domain.model.Module;
import com.falanadamian.krim.schedule.domain.model.ModuleGeneralInformation;
import com.falanadamian.krim.schedule.domain.model.UserSignature;
import com.falanadamian.krim.schedule.exception.BadRequestException;
import com.falanadamian.krim.schedule.scrapper.ModuleScrapper;
import com.falanadamian.krim.schedule.scrapper.SemesterData;
import com.falanadamian.krim.schedule.service.ModuleGeneralInformationService;
import com.falanadamian.krim.schedule.service.ModuleService;
import com.falanadamian.krim.schedule.service.UserSignatureService;
import com.falanadamian.krim.schedule.util.HeaderGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/krim")
@CrossOrigin(origins = "*", exposedHeaders = HeaderGenerator.KRIM_SCHEDULE_MANAGEMENT_SYSTEM, maxAge = 3600)
public class ScrapperController {

    private static final String API_URI = "/krim/scrapper/";
    private static final String CONTROLLER_NAME = "SCRAPPER";

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ModuleService moduleService;
    private final ModuleGeneralInformationService moduleGeneralInformationService;
    private final UserSignatureService userSignatureService;
    private final ModuleMapper moduleMapper;
    private final ModuleScrapper moduleScrapper;


    public ScrapperController(SimpMessagingTemplate simpMessagingTemplate, ModuleService moduleService, ModuleGeneralInformationService moduleGeneralInformationService, UserSignatureService userSignatureService, ModuleMapper moduleMapper, ModuleScrapper moduleScrapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.moduleService = moduleService;
        this.moduleMapper = moduleMapper;
        this.moduleScrapper = moduleScrapper;
        this.moduleGeneralInformationService = moduleGeneralInformationService;
        this.userSignatureService = userSignatureService;
    }

    @PostMapping("/scrapper/faculty/verify/duplicate")
    public ResponseEntity<List<ModuleDTO>> scrapFacultyVerifyDuplicate(@RequestBody String URL) throws URISyntaxException {
        if (StringUtils.isBlank(URL)) {
            throw new BadRequestException("Requested URL is not valid");
        }

        List<SemesterData> semesterDataList = this.moduleScrapper.scrapStudiesData(URL, simpMessagingTemplate);
        List<Module> unsavedModulesList = new ArrayList<>();
        List<Module> savedModulesList = new ArrayList<>();

        semesterDataList.forEach(semesterData -> {
            semesterData.getModules().forEach(module -> {
                if (moduleService.existsByCode(module.getCode())) {
                    unsavedModulesList.add(module);
                } else {
                    /*ModuleGeneralInformation moduleGeneralInformation = module.getModuleGeneralInformation();
                    UserSignature responsibleUserSignature = moduleGeneralInformation.getResponsibleUserSignature();
                    responsibleUserSignature = userSignatureService.save(responsibleUserSignature);
                    moduleGeneralInformation.setResponsibleUserSignature(responsibleUserSignature);

                    List<UserSignature> academicUserSignatures =  moduleGeneralInformation.getAcademicUserSignatures();
                    academicUserSignatures.stream().map( academicUserSignature -> {
                        Optional<UserSignature> userSignature = userSignatureService.findOneByEmail(academicUserSignature.getEmail());
                        if(userSignature.isPresent()) {
                            return userSignature;
                        } else {
                            return userSignatureService.save(academicUserSignature);
                        }
                    });
                    moduleGeneralInformation.setAcademicUserSignatures(academicUserSignatures);

                    moduleGeneralInformation = moduleGeneralInformationService.save(module.getModuleGeneralInformation());
                    module.setModuleGeneralInformation(moduleGeneralInformation);
                    module = moduleService.saveModule(module);
                    savedModulesList.add(module);*/
                }
            });
        });

        String savedModulesIds = savedModulesList.stream().map(module -> module.getId().toString()).collect(Collectors.joining(","));
        List<ModuleDTO> unsavedModulesDtos = unsavedModulesList.stream().map(module -> moduleMapper.toDto(module)).collect(Collectors.toList());

        return ResponseEntity
                .created(new URI(API_URI))
                .headers(HeaderGenerator.generateScrapperHeader(CONTROLLER_NAME, savedModulesIds))
                .body(unsavedModulesDtos);
    }

    /*@PostMapping("/scrapper/course/verify/duplicate")
    public ResponseEntity<List<ModuleDTO>> scrapCourseVerifyDuplicate(@RequestBody List<String> URLs) throws URISyntaxException {
        if (URLs.stream().noneMatch(StringUtils::isNotBlank)) {
            throw new BadRequestException("Requested URLs are not valid");
        }

        URLs = URLs.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

        List<SemesterData> semesterDataList = new ArrayList<>();
        for (String URL : URLs) {
            List<SemesterData> scrappedSemesterData = this.moduleScrapper.scrapStudiesData(URL, simpMessagingTemplate);
            semesterDataList = Stream.concat(semesterDataList.stream(), scrappedSemesterData.stream()).collect(Collectors.toList());
        }

        List<Module> unsavedModulesList = new ArrayList<>();
        List<Module> savedModulesList = new ArrayList<>();

        semesterDataList.forEach(semesterData -> {
            semesterData.getModules().forEach(module -> {
                if (!moduleService.existsByCode(module.getCode())) {

                    ModuleGeneralInformation moduleGeneralInformation = module.getModuleGeneralInformation();

                    UserSignature responsibleUserSignature = moduleGeneralInformation.getResponsibleUserSignature();
                    if(Objects.nonNull(responsibleUserSignature)) {
                        Optional<UserSignature> dbResponsibleUserSignature = userSignatureService.findOneByEmail(responsibleUserSignature.getEmail());
                        if (dbResponsibleUserSignature.isPresent()) {
                            moduleGeneralInformation.setResponsibleUserSignature(dbResponsibleUserSignature.get());
                        } else {
                            responsibleUserSignature = userSignatureService.save(responsibleUserSignature);
                            moduleGeneralInformation.setResponsibleUserSignature(responsibleUserSignature);
                        }
                    }

                    List<UserSignature> academicUserSignatures =  moduleGeneralInformation.getAcademicUserSignatures();
                    if(Objects.nonNull(academicUserSignatures) && !academicUserSignatures.isEmpty()) {
                        academicUserSignatures.stream().map(academicUserSignature -> {
                            Optional<UserSignature> userSignature = userSignatureService.findOneByEmail(academicUserSignature.getEmail());
                            if (userSignature.isPresent()) {
                                return userSignature.get();
                            } else {
                                return userSignatureService.save(academicUserSignature);
                            }
                        });
                    } else {
                        academicUserSignatures = new ArrayList<>();
                    }
                    moduleGeneralInformation.setAcademicUserSignatures(academicUserSignatures);

                    moduleGeneralInformation = moduleGeneralInformationService.save(module.getModuleGeneralInformation());
                    module.setModuleGeneralInformation(moduleGeneralInformation);
                    module = moduleService.saveModule(module);
                    savedModulesList.add(module);
                } else {
                    unsavedModulesList.add(module);
                }
            });
        });

        String savedModulesIds = savedModulesList.stream().map(module -> module.getId().toString()).collect(Collectors.joining(","));
        List<ModuleDTO> unsavedModulesDtos = unsavedModulesList.stream().map(module -> moduleMapper.toDto(module)).collect(Collectors.toList());

        return ResponseEntity
                .created(new URI(API_URI))
                .headers(HeaderGenerator.generateScrapperHeader(CONTROLLER_NAME, savedModulesIds))
                .body(unsavedModulesDtos);
    }*/


    @PostMapping("/scrapper/course/verify/all")
    public ResponseEntity<List<ModuleDTO>> scrapCourseVerifyAll(@RequestBody List<String> URLs) {

        if (URLs.stream().noneMatch(StringUtils::isNotBlank)) {
            throw new BadRequestException("Requested URLs are not valid");
        }

        URLs = URLs.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

        List<SemesterData> semesterDataList = new ArrayList<>();
        for (String URL : URLs) {
            List<SemesterData> scrappedSemesterData = this.moduleScrapper.scrapStudiesData(URL, simpMessagingTemplate);
            semesterDataList = Stream.concat(semesterDataList.stream(), scrappedSemesterData.stream()).collect(Collectors.toList());
        }

        List<Module> modules = new ArrayList<>();

        semesterDataList.forEach(semesterData -> modules.addAll(semesterData.getModules()));

        List<ModuleDTO> modulesDto = modules.stream().map(module -> moduleMapper.toDto(module)).collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(modulesDto);
    }

    /*@MessageMapping("send/message")
    public void onReceivedMessage(String message) {
        this.simpMessagingTemplate.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + "- " + message);
    }*/

    @MessageMapping("send/message")
    public void onReceivedMessage(String message) throws URISyntaxException {
        /*if (URLs.stream().noneMatch(StringUtils::isNotBlank)) {
            throw new BadRequestException("Requested URLs are not valid");
        }*/

//        URLs = URLs.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());

        List<SemesterData> semesterDataList = new ArrayList<>();

        List<SemesterData> scrappedSemesterData = this.moduleScrapper.scrapStudiesData(message, simpMessagingTemplate);
        semesterDataList = Stream.concat(semesterDataList.stream(), scrappedSemesterData.stream()).collect(Collectors.toList());


        List<Module> unsavedModulesList = new ArrayList<>();
        List<Module> savedModulesList = new ArrayList<>();

        semesterDataList.forEach(semesterData -> {
            semesterData.getModules().forEach(module -> {
                if (!moduleService.existsByCode(module.getCode())) {

                    ModuleGeneralInformation moduleGeneralInformation = module.getModuleGeneralInformation();

                    UserSignature responsibleUserSignature = moduleGeneralInformation.getResponsibleUserSignature();
                    if(Objects.nonNull(responsibleUserSignature)) {
                        Optional<UserSignature> dbResponsibleUserSignature = userSignatureService.findOneByEmail(responsibleUserSignature.getEmail());
                        if (dbResponsibleUserSignature.isPresent()) {
                            moduleGeneralInformation.setResponsibleUserSignature(dbResponsibleUserSignature.get());
                        } else {
                            responsibleUserSignature = userSignatureService.save(responsibleUserSignature);
                            moduleGeneralInformation.setResponsibleUserSignature(responsibleUserSignature);
                        }
                    }

                    List<UserSignature> academicUserSignatures =  moduleGeneralInformation.getAcademicUserSignatures();
                    if(Objects.nonNull(academicUserSignatures) && !academicUserSignatures.isEmpty()) {
                        academicUserSignatures.stream().map(academicUserSignature -> {
                            Optional<UserSignature> userSignature = userSignatureService.findOneByEmail(academicUserSignature.getEmail());
                            if (userSignature.isPresent()) {
                                return userSignature.get();
                            } else {
                                return userSignatureService.save(academicUserSignature);
                            }
                        });
                    } else {
                        academicUserSignatures = new ArrayList<>();
                    }
                    moduleGeneralInformation.setAcademicUserSignatures(academicUserSignatures);

                    moduleGeneralInformation = moduleGeneralInformationService.save(module.getModuleGeneralInformation());
                    module.setModuleGeneralInformation(moduleGeneralInformation);
                    module = moduleService.saveModule(module);
                    savedModulesList.add(module);
                } else {
                    unsavedModulesList.add(module);
                }
            });
        });

        String savedModulesIds = savedModulesList.stream().map(module -> module.getId().toString()).collect(Collectors.joining(","));
        List<ModuleDTO> unsavedModulesDtos = unsavedModulesList.stream().map(module -> moduleMapper.toDto(module)).collect(Collectors.toList());
    }

}
