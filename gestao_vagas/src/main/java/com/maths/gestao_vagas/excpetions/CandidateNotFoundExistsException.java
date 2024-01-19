package com.maths.gestao_vagas.excpetions;

public class CandidateNotFoundExistsException extends RuntimeException{
    public CandidateNotFoundExistsException(){
        super("Candidate with email not already exists");
    }
}
