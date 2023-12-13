package com.example.app.modules.company;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.modules.company.entity.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
  
}
