package com.skillspherenexus.skillmanagementservice.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class EmployeeCompetencyResponseDTO {

    private UUID employeeCompetencyId;

    private UUID employeeId;

    private UUID competencyId;

    private Integer currentLevel;
}
