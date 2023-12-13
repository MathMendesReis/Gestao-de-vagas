package com.example.app.modules.company.exeception;


public class CompanyNotFoundException extends RuntimeException{
   public CompanyNotFoundException(){
    super("Empresa não encontrada/cadastrada");
  }
}