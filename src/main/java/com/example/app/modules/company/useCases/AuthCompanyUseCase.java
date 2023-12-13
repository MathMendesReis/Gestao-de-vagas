package com.example.app.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.app.modules.company.CompanyRepository;
import com.example.app.modules.company.dto.AuthCompanyDTO;

@Service
public class AuthCompanyUseCase {

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
    var company = this.companyRepository.findByCompanyName(authCompanyDTO.getCompanyName()).orElseThrow(()->{
      throw new UsernameNotFoundException("username/password not found");
    });

    var passwordMacthes = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMacthes) {
      throw new AuthenticationException();
    }
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("javagas")
    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
    .withSubject(company.getId().toString())
    .sign(algorithm);
    return token;
  }
}
