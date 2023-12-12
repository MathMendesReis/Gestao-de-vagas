package com.example.app.modules.company.exeception;

public class EmailFoundException extends RuntimeException {
  
  public EmailFoundException(){
    super("Empresa ja cadastrada");
  }
}
