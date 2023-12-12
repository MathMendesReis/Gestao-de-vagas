package com.example.app.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.modules.company.JobRepository;
import com.example.app.modules.company.entity.JobEntity;

import jakarta.validation.Valid;

@Service
public class CreatedJobUseCase {
  @Autowired
  private JobRepository jobRepository;
  public JobEntity execute(@Valid JobEntity jobEntity){
    return this.jobRepository.save(jobEntity);
  }
}
