package com.example.app.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.company.entity.CompanyEntity;
import com.example.app.modules.company.useCases.CreatedCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
  @Autowired
  private CreatedCompanyUseCase createdCompanyUseCase;
  @PostMapping("/")
  public @Valid ResponseEntity<Object> created(@RequestBody @Valid CompanyEntity companyEntity){
    try {
      var result = this.createdCompanyUseCase.execute(companyEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
    public String companyPage() {
        return "company";
    }

}
