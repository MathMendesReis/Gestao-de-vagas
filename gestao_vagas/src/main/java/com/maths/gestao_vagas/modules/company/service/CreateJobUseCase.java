package com.maths.gestao_vagas.modules.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maths.gestao_vagas.excpetions.CompanyNotFoundException;
import com.maths.gestao_vagas.modules.company.models.JobEntity;
import com.maths.gestao_vagas.modules.company.repository.CompanyRepository;
import com.maths.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {
      @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }
}
