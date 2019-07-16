package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    boolean existsByCode(String code);

    Optional<List<Module>> findAllByIdIn(List<Long> ids);

    List<Module> findAllByModuleGeneralInformation_ResponsibleTeacher_Username(String username);

}
