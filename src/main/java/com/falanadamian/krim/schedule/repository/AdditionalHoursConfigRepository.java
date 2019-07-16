package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalHoursConfigRepository extends JpaRepository<AdditionalHoursConfig, Long> {

}
