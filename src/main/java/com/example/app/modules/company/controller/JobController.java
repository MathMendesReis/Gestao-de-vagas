package com.example.app.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.company.JobRepository;
import com.example.app.modules.company.dto.CreatedJobDTO;
import com.example.app.modules.company.entity.JobEntity;
import com.example.app.modules.company.useCases.JobCreatedUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private JobCreatedUseCase jobCreatedUseCase;

 @PostMapping("/")
  public @Valid ResponseEntity<Object> create(@Valid @RequestBody CreatedJobDTO createdJobDTO, HttpServletRequest request){
    try {
      var companyId = request.getAttribute("company_id");
      JobEntity jobEntity = JobEntity.builder()
      .benefits(createdJobDTO.getBenefits())
      .companyId(UUID.fromString(companyId.toString()))
      .description(createdJobDTO.getDescription())
      .level(createdJobDTO.getLevel())
      .build();

      var result = jobCreatedUseCase.execute(jobEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
 
}
