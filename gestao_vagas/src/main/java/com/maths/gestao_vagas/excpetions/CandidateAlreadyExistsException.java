package com.maths.gestao_vagas.excpetions;

public class CandidateAlreadyExistsException extends RuntimeException {
    public CandidateAlreadyExistsException(){
        super("Candidate with email already exists");
    }
}
