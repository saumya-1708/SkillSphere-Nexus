package com.skillspherenexus.skillmanagementservice.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillspherenexus.skillmanagementservice.entity.EmployeeCompetency;

public interface EmployeeCompetencyRepository extends JpaRepository<EmployeeCompetency, UUID> {

    List<EmployeeCompetency> findByEmployeeId(UUID employeeId);

    Optional<EmployeeCompetency> findByEmployeeIdAndCompetency_CompetencyId(
            UUID employeeId,
            UUID competencyId
    );
}