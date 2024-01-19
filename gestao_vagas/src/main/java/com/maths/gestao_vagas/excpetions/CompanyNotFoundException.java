package com.maths.gestao_vagas.excpetions;

public class CompanyNotFoundException extends RuntimeException  {
    public CompanyNotFoundException(){
        super("Company with email not already exists");
    }
}
