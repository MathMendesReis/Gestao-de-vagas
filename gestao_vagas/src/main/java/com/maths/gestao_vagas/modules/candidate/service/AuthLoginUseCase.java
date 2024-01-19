package com.maths.gestao_vagas.modules.candidate.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.maths.gestao_vagas.excpetions.CandidateNotFoundExistsException;
import com.maths.gestao_vagas.modules.candidate.dto.AuthLoginRequestDTO;
import com.maths.gestao_vagas.modules.candidate.dto.AuthLoginResponseDTO;
import com.maths.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class AuthLoginUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    public AuthLoginResponseDTO execute (AuthLoginRequestDTO authLoginRequestDTO) throws Exception{
        if (authLoginRequestDTO == null) {
            throw new Exception();
        }

        var candidate = this.candidateRepository.findByEmail(authLoginRequestDTO.email()).orElseThrow(() ->{
            throw new CandidateNotFoundExistsException();
        });
        var passwordMatches = this.passwordEncoder
        .matches(authLoginRequestDTO.password(), candidate.getPassword());

        if (!passwordMatches) {
        throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
        .withIssuer("javagas")
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("CANDIDATE"))
        .withExpiresAt(expiresIn)
        .sign(algorithm);

    var AuthCandidateResponse = AuthLoginResponseDTO.builder()
        .access_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

        return AuthCandidateResponse;
    }
}
