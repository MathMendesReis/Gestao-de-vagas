package com.example.app.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @PostMapping("/")
  public void created(@Valid CandidateEntity candidateEntity){
    System.out.println("Candidato");
    System.out.println(candidateEntity.getEmail());  
  }
}
