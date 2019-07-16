package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<List<Schedule>> findAllByIdIn(List<Long> ids);

    List<Schedule> findAllByAssigneeUsername(String username);

}
