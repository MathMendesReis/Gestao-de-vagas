package com.example.app.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.modules.company.CompanyRepository;
import com.example.app.modules.company.entity.CompanyEntity;
import com.example.app.modules.company.exeception.EmailFoundException;

@Service
public class CreatedCompanyUseCase {

  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity){
      this.companyRepository.findByEmail(companyEntity.getEmail()).ifPresent(user -> {
          throw new EmailFoundException();
        });
      return companyRepository.save(companyEntity);

  }
  
}
