package com.falanadamian.krim.schedule.repository;

import com.falanadamian.krim.schedule.domain.model.UserSignature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSignatureRepository extends JpaRepository<UserSignature, Long> {

    UserSignature getById(Long id);

    Optional<UserSignature> findByEmail(String email);
}
