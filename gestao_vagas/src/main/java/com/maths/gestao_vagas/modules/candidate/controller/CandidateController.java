package com.maths.gestao_vagas.modules.candidate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.gestao_vagas.modules.candidate.dto.CandidateCreateRequestDTO;
import com.maths.gestao_vagas.modules.candidate.service.SaveCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private SaveCandidateUseCase saveCandidateUseCase;

    @PostMapping("/create-account")
    public ResponseEntity<Object> save(@RequestBody @Valid CandidateCreateRequestDTO body){
        try {
            var result = this.saveCandidateUseCase.saveCandidate(body);
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
