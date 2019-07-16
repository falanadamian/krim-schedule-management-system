package com.falanadamian.krim.schedule.service;

import com.falanadamian.krim.schedule.domain.dto.StudyInfoDTO;
import com.falanadamian.krim.schedule.domain.mapper.StudyInfoMapper;
import com.falanadamian.krim.schedule.domain.model.StudyInfo;
import com.falanadamian.krim.schedule.repository.StudyInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudyInfoService {

    private final StudyInfoRepository studyInfoRepository;
    private final StudyInfoMapper studyInfoMapper;

    public StudyInfoService(StudyInfoRepository studyInfoRepository, StudyInfoMapper studyInfoMapper) {
        this.studyInfoRepository = studyInfoRepository;
        this.studyInfoMapper = studyInfoMapper;
    }

    public StudyInfoDTO save(StudyInfoDTO studyInfoDTO) {
        StudyInfo studyInfo = studyInfoMapper.toEntity(studyInfoDTO);
        studyInfo = studyInfoRepository.save(studyInfo);
        return studyInfoMapper.toDto(studyInfo);
    }

    @Transactional(readOnly = true)
    public List<StudyInfoDTO> findAll() {
        return studyInfoRepository
                .findAll()
                .stream()
                .map(studyInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<StudyInfoDTO> findAll(Pageable pageable) {
        return studyInfoRepository
                .findAll(pageable)
                .map(studyInfoMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<StudyInfoDTO> findAllWhereUserIsNull() {
        return studyInfoRepository.findAll().stream()
                .filter(studyInfo -> studyInfo.getUser() == null)
                .map(studyInfoMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Optional<StudyInfoDTO> findOne(Long id) {
        return studyInfoRepository
                .findById(id)
                .map(studyInfoMapper::toDto);
    }

    public void delete(Long id) {
        studyInfoRepository.deleteById(id);
    }
}
