package com.example.app.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateEntity,UUID>{
  Optional<CandidateEntity> findByUserNameOrEmail(String UserName, String Email);
}
