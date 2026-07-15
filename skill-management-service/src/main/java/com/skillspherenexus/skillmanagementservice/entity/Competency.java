package com.skillspherenexus.skillmanagementservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.UUID;

@Entity
@Table(name = "competencies")
public class Competency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID competencyId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompetencyCategory category;

    private String description;

    @Min(value = 1, message = "Max level must be at least 1")
    @Max(value = 10, message = "Max level cannot exceed 10")
    private Integer maxLevel = 10;

    // Getters and Setters
    public UUID getCompetencyId() { return competencyId; }
    public void setCompetencyId(UUID competencyId) { this.competencyId = competencyId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CompetencyCategory getCategory() { return category; }
    public void setCategory(CompetencyCategory category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getMaxLevel() { return maxLevel; }
    public void setMaxLevel(Integer maxLevel) { this.maxLevel = maxLevel; }
}
