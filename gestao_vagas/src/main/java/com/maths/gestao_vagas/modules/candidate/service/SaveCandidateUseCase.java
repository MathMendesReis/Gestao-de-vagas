package com.maths.gestao_vagas.modules.candidate.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maths.gestao_vagas.excpetions.CandidateAlreadyExistsException;
import com.maths.gestao_vagas.modules.candidate.dto.CandidateCreateRequestDTO;
import com.maths.gestao_vagas.modules.candidate.models.CandidateModels;
import com.maths.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class SaveCandidateUseCase {
    @Autowired
    private CandidateRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;
    public CandidateModels saveCandidate( CandidateCreateRequestDTO candidateCreateRequestDTO) throws Exception{
        if (candidateCreateRequestDTO == null) {
            throw new Exception();
        }

        this.repository.findByEmail(candidateCreateRequestDTO.email()).ifPresent(user ->{
            throw new CandidateAlreadyExistsException();
        });
         var candidate = new CandidateModels();
         BeanUtils.copyProperties(candidateCreateRequestDTO, candidate);
         
         var password = passwordEncoder.encode(candidate.getPassword());
         candidate.setPassword(password);
     
         
         var result = this.repository.save(candidate);


         return result;
    }
}
