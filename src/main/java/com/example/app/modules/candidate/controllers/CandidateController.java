package com.example.app.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.candidate.CandidateEntity;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @PostMapping("/")
  public void created(CandidateEntity candidateEntity){
    System.out.println("Candidato");
    System.out.println(candidateEntity.getEmail());  }
}
