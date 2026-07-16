package com.skillspherenexus.skillmanagementservice.dto;

import com.skillspherenexus.skillmanagementservice.entity.CompetencyCategory;
import lombok.Data;

import java.util.UUID;

@Data
public class CompetencyResponseDTO {

    private UUID competencyId;

    private String name;

    private CompetencyCategory category;

    private String description;

    private Integer maxLevel;
}
