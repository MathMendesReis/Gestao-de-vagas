package com.example.app.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.modules.company.CompanyRepository;
import com.example.app.modules.company.JobRepository;
import com.example.app.modules.company.entity.JobEntity;
import com.example.app.modules.company.exeception.CompanyNotFoundException;

@Service
public class JobCreatedUseCase {
  @Autowired
  private JobRepository jobRepository;

  
  @Autowired
  private CompanyRepository companyRepository;

  public JobEntity execute(JobEntity jobEntity) {
    companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
      throw new CompanyNotFoundException();
    });
    return jobRepository.save(jobEntity);
  }
}
