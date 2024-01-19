package com.maths.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.gestao_vagas.modules.company.models.CompanyEntity;
import com.maths.gestao_vagas.modules.company.service.CreateCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
@Autowired
private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("/create-account")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {

    try {
      var result = this.createCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
