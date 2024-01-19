package com.maths.gestao_vagas.modules.candidate.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "tb_candidate")
@Data
public class CandidateModels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank @Email
    private String email;
    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    @Pattern(regexp = "/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])(?:([0-9a-zA-Z$*&@#])(?!\\1)){8,}$/i\n")
    // a12B@cde
    private String password;
    @NotBlank
    private String description;
    private String curriculum;
    @CreationTimestamp
    private LocalDateTime createdAt;
}

