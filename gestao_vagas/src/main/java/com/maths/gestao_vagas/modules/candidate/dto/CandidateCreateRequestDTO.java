package com.maths.gestao_vagas.modules.candidate.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CandidateCreateRequestDTO(
    @NotBlank(message = "O nome não pode estar em branco")
    String name,
    @NotBlank 
    @Email
    String email,
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])(?:([0-9a-zA-Z$*&@#])(?!\\1)){8,}$/i\n")
    String password,
    @NotBlank
    String description,
    String curriculum
) {

}
