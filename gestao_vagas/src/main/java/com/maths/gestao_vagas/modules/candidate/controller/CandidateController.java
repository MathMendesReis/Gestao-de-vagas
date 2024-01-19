package com.maths.gestao_vagas.modules.candidate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.gestao_vagas.modules.candidate.dto.CandidateCreateRequestDTO;
import com.maths.gestao_vagas.modules.candidate.models.CandidateModels;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class CandidateController {
    
    public ResponseEntity<Object> save(@RequestBody @Valid CandidateCreateRequestDTO body){
        try {

            return ResponseEntity.status(HttpStatus.OK)
            .body("");

        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    }
}
