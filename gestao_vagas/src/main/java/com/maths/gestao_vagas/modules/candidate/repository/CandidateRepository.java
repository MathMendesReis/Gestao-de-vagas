package com.maths.gestao_vagas.modules.candidate.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maths.gestao_vagas.modules.candidate.models.CandidateModels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public interface CandidateRepository extends JpaRepository<CandidateModels, UUID>{

    Optional<CandidateModels> findByEmail(@NotBlank @Email String email);
    
}
