package com.maths.gestao_vagas.modules.candidate.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CandidateCreateRequestDTO(
    @NotBlank(message = "O nome não pode estar em branco")
    String name,
    @NotBlank 
    @Email
    String email,
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    String password,
    @NotBlank
    String description,
    String curriculum
) {

}

