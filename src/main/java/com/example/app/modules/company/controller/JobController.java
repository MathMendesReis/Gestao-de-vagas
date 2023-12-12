package com.example.app.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.company.entity.JobEntity;
import com.example.app.modules.company.useCases.CreatedJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value =  "/job")
public class JobController {
  @Autowired
  private CreatedJobUseCase createdJobUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> created(@Valid JobEntity jobEntity){
    try {
      this.createdJobUseCase.execute(jobEntity);
      return ResponseEntity.ok().body("Job adicionado com sucesso.");

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
