package com.skillspherenexus.skillmanagementservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillspherenexus.skillmanagementservice.dto.GapResult;
import com.skillspherenexus.skillmanagementservice.entity.Competency;
import com.skillspherenexus.skillmanagementservice.entity.CompetencyFramework;
import com.skillspherenexus.skillmanagementservice.entity.EmployeeCompetency;
import com.skillspherenexus.skillmanagementservice.service.CompetencyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/competencies")
public class CompetencyController {

    private final CompetencyService competencyService;

    public CompetencyController(CompetencyService competencyService) {
        this.competencyService = competencyService;
    }

    // --- Competency Catalog ---

    @PostMapping
    public Competency create(@Valid @RequestBody Competency competency) {
        return competencyService.create(competency);
    }

    @GetMapping
    public List<Competency> getAll() {
        return competencyService.getAll();
    }

    @GetMapping("/{id}")
    public Competency getById(@PathVariable UUID id) {
        return competencyService.getById(id);
    }

    @PutMapping("/{id}")
    public Competency update(@PathVariable UUID id, @RequestBody Competency competency) {
        return competencyService.update(id, competency);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        competencyService.delete(id);
    }

    // --- Competency Framework ---

    @PostMapping("/frameworks")
    public CompetencyFramework defineFramework(@RequestBody CompetencyFramework framework) {
        return competencyService.defineFrameworkRequirement(framework);
    }

    @GetMapping("/frameworks/role/{role}")
    public List<CompetencyFramework> getFrameworkByRole(@PathVariable String role) {
        return competencyService.getFrameworkForRole(role);
    }

    // --- Employee Competency ---

    @PostMapping("/employee-levels")
    public EmployeeCompetency recordEmployeeLevel(@RequestBody EmployeeCompetency employeeCompetency) {
        return competencyService.recordEmployeeLevel(employeeCompetency);
    }

    // --- Gap Analysis ---

    @GetMapping("/gap-analysis")
    public List<GapResult> getGapAnalysis(@RequestParam UUID employeeId, @RequestParam String role) {
        return competencyService.analyzeGap(employeeId, role);
    }
}
