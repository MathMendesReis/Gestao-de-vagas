package com.maths.gestao_vagas.modules.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public record AuthLoginRequestDTO(@NotBlank @Email String email, @NotBlank String password) {
}  