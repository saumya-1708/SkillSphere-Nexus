package com.skillspherenexus.skillmanagementservice.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "competency_frameworks")
public class CompetencyFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID frameworkId;

    @Column(nullable = false)
    private String role;

    @ManyToOne
    @JoinColumn(name = "competency_id", nullable = false)
    private Competency competency;

    @Column(nullable = false)
    private Integer requiredLevel;

    // Getters and Setters
    public UUID getFrameworkId() { return frameworkId; }
    public void setFrameworkId(UUID frameworkId) { this.frameworkId = frameworkId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Competency getCompetency() { return competency; }
    public void setCompetency(Competency competency) { this.competency = competency; }

    public Integer getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(Integer requiredLevel) { this.requiredLevel = requiredLevel; }
}
