package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyInfoRepository extends JpaRepository<StudyInfo, Long> {

}
