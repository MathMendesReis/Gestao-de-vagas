package com.maths.gestao_vagas.modules.candidate.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maths.gestao_vagas.modules.candidate.dto.AuthLoginRequestDTO;
import com.maths.gestao_vagas.modules.candidate.service.AuthLoginUseCase;

@RequestMapping("/auth")
@RestController
public class AuthController {
    
    @Autowired
    private AuthLoginUseCase authLoginUseCase;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody AuthLoginRequestDTO authLoginRequestDTO){
        try {
            var result = this.authLoginUseCase.execute(authLoginRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            
        }
    }
}
