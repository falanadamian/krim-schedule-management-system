package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.*;
import com.falanadamian.krim.schedule.domain.model.enumeration.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConfigRepository extends JpaRepository<UserConfig, Position> {

}
