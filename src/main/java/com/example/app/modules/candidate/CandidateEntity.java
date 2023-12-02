package com.example.app.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class CandidateEntity {

  @Id
  private UUID id;
  private String name;
  @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não deve conter espaço")
  private String userName;
  @Email(message = "O campo deve conter uma email válido.")
  private String email;
  @Length(min = 10, max = 100)
  private String password;
  private String description;
  private String curriculum;

}
