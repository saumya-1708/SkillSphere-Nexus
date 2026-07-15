package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.UUID;

import com.skillspherenexus.skillmanagementservice.dto.GapResult;
import com.skillspherenexus.skillmanagementservice.entity.Competency;
import com.skillspherenexus.skillmanagementservice.entity.CompetencyFramework;
import com.skillspherenexus.skillmanagementservice.entity.EmployeeCompetency;

public interface CompetencyService {

    // Competency
    Competency create(Competency competency);

    List<Competency> getAll();

    Competency getById(UUID id);

    Competency update(UUID id, Competency competency);

    void delete(UUID id);

    // Competency Framework
    CompetencyFramework defineFrameworkRequirement(CompetencyFramework framework);

    List<CompetencyFramework> getFrameworkForRole(String role);

    // Employee Competency
    EmployeeCompetency recordEmployeeLevel(EmployeeCompetency employeeCompetency);

    // Gap Analysis
    List<GapResult> analyzeGap(UUID employeeId, String targetRole);

}