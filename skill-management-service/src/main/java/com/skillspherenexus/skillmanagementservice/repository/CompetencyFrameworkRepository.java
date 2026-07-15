package com.skillspherenexus.skillmanagementservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillspherenexus.skillmanagementservice.entity.CompetencyFramework;

public interface CompetencyFrameworkRepository extends JpaRepository<CompetencyFramework, UUID> {

    List<CompetencyFramework> findByRole(String role);
}
