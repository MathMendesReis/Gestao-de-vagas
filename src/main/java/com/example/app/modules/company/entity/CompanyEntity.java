package com.example.app.modules.company.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String companyName;
  private String companyDescription;
  private String website;


  @Email(message = "O campo [email] deve conter um e-mail válido")
  private String email;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
