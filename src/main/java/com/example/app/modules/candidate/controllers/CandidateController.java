package com.example.app.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.candidate.CandidateEntity;
import com.example.app.modules.candidate.CandidateRepository;
import com.example.app.modules.candidate.UserFoundException;
import com.example.app.modules.candidate.useCases.CreatedCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreatedCandidateUseCase createdCandidateUseCase;
  @PostMapping("/")
  public @Valid ResponseEntity<Object> created(@RequestBody  @Valid CandidateEntity candidateEntity){
    try {
      var result = this.createdCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
