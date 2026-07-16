package com.skillspherenexus.skillmanagementservice.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CompetencyFrameworkResponseDTO {

    private UUID frameworkId;

    private String role;

    private UUID competencyId;

    private Integer requiredLevel;
}
