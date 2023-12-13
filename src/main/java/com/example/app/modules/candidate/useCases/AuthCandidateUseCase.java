package com.example.app.modules.candidate.useCases;

import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.app.modules.candidate.CandidateRepository;
import com.example.app.modules.candidate.dto.AuthCandidateResponseDTO;
import com.example.app.modules.candidate.dto.AuthCandidateResquestDTO;

import ch.qos.logback.core.util.Duration;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;
  public AuthCandidateResponseDTO execute(AuthCandidateResquestDTO authCandidateResquestDTO) throws AuthenticationException{
    var candidate = this.candidateRepository.findByUserName(authCandidateResquestDTO.userName())
      .orElseThrow(()->{
        throw new UsernameNotFoundException("username/password incorrect.");
      });
      var passwordMatches = this.passwordEncoder.matches(authCandidateResquestDTO.password(),candidate.getPassword());
      if (!passwordMatches) {
        throw new AuthenticationException();
      }

      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      var token = JWT.create()
      .withIssuer("javagas")
      .withSubject(candidate.getId().toString())
      .withClaim("roles", Arrays.asList("candidate"))
      .withExpiresAt(Instant.now().plus(java.time.Duration.ofHours(2)))
      .sign(algorithm);

      var authCandidateResponse = AuthCandidateResponseDTO.builder()
      .acess_token(token)
      .build();

      return authCandidateResponse;
  }

}
