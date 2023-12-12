package com.example.app.modules.company;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.modules.company.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity,UUID> {
  Optional<CompanyEntity> findByEmail (String email);
}
