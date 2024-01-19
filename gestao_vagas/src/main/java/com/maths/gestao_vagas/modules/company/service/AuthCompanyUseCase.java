package com.maths.gestao_vagas.modules.company.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maths.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.maths.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import com.maths.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
          });

          var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

          if (!passwordMatches) {
            throw new AuthenticationException();
          }
      
          Algorithm algorithm = Algorithm.HMAC256(secretKey);
      
          var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
      
          var token = JWT.create().withIssuer("javagas")
              .withExpiresAt(expiresIn)
              .withSubject(company.getId().toString())
              .withClaim("roles", Arrays.asList("COMPANY"))
              .sign(algorithm);
      
          var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
              .access_token(token)
              .expires_in(expiresIn.toEpochMilli())
              .build();
      
          return authCompanyResponseDTO;
    }
}
