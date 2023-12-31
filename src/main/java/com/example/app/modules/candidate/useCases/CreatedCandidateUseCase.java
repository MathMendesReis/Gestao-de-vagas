package com.example.app.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.modules.candidate.CandidateEntity;
import com.example.app.modules.candidate.CandidateRepository;
import com.example.app.modules.candidate.UserFoundException;

@Service
public class CreatedCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;


  public CandidateEntity execute(CandidateEntity candidateEntity){
    //validação
     this.candidateRepository.findByUserNameOrEmail(candidateEntity.getUserName(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundException();
        });
      String password =  passwordEncoder.encode(candidateEntity.getPassword());
      candidateEntity.setPassword(password);
    
    return this.candidateRepository.save(candidateEntity);

  }
}
