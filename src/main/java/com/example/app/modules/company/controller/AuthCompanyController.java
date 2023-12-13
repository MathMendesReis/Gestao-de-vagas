package com.example.app.modules.company.controller;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.modules.company.dto.AuthCompanyDTO;
import com.example.app.modules.company.useCases.AuthCompanyUseCase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUseCase authCompanyUseCase;
  
  @PostMapping("/company")
  public ResponseEntity<Object> created(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
    try {
      var result = this.authCompanyUseCase.execute(authCompanyDTO);
      return ResponseEntity.ok().body(result);
      
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
