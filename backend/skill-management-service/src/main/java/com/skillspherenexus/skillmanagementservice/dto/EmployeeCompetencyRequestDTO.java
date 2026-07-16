package com.skillspherenexus.skillmanagementservice.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeCompetencyRequestDTO {

    @NotNull
    private UUID employeeId;

    @NotNull
    private UUID competencyId;

    @NotNull
    private Integer currentLevel;
}
