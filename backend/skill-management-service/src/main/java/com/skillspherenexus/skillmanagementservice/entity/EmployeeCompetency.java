package com.skillspherenexus.skillmanagementservice.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "employee_competencies")
public class EmployeeCompetency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID employeeCompetencyId;

    @Column(nullable = false)
    private UUID employeeId;

    @ManyToOne
    @JoinColumn(name = "competency_id", nullable = false)
    private Competency competency;

    @Column(nullable = false)
    private Integer currentLevel;

    public EmployeeCompetency() {
    }

    public EmployeeCompetency(UUID employeeCompetencyId, UUID employeeId, Competency competency, Integer currentLevel) {
        this.employeeCompetencyId = employeeCompetencyId;
        this.employeeId = employeeId;
        this.competency = competency;
        this.currentLevel = currentLevel;
    }

    // Getters and Setters
    public UUID getEmployeeCompetencyId() { return employeeCompetencyId; }
    public void setEmployeeCompetencyId(UUID employeeCompetencyId) { this.employeeCompetencyId = employeeCompetencyId; }

    public UUID getEmployeeId() { return employeeId; }
    public void setEmployeeId(UUID employeeId) { this.employeeId = employeeId; }

    public Competency getCompetency() { return competency; }
    public void setCompetency(Competency competency) { this.competency = competency; }

    public Integer getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(Integer currentLevel) { this.currentLevel = currentLevel; }
}