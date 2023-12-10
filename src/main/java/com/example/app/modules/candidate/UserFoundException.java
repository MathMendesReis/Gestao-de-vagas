package com.example.app.modules.candidate;

public class UserFoundException extends RuntimeException {
  public UserFoundException(){
    super("Usuario ja existe");
  }
}
