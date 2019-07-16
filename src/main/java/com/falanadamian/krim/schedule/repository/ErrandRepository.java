package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErrandRepository extends JpaRepository<Errand, Long> {

    Optional<Errand> getByModuleCode(String code);

    Optional<List<Errand>> findAllByIdIn(List<Long> ids);


}
