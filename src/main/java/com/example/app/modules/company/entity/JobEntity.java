package com.example.app.modules.company.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

   @NotBlank
   private String description;

   @NotBlank
   private String benefits;

   @NotBlank
   private String level;

   @ManyToOne
   @JoinColumn(name = "company_id", insertable = false, updatable = false)
   private CompanyEntity companyEntity;

   @Column(name = "company_id", nullable = false)
   private UUID companyId;
   @CreationTimestamp
   private LocalDateTime createdAt;

}
