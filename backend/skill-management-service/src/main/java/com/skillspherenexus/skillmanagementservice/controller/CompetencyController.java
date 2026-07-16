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

import com.skillspherenexus.skillmanagementservice.dto.CompetencyFrameworkRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.CompetencyFrameworkResponseDTO;
import com.skillspherenexus.skillmanagementservice.dto.CompetencyRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.CompetencyResponseDTO;
import com.skillspherenexus.skillmanagementservice.dto.EmployeeCompetencyRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.EmployeeCompetencyResponseDTO;
import com.skillspherenexus.skillmanagementservice.dto.GapResult;
import com.skillspherenexus.skillmanagementservice.service.CompetencyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/competencies")
public class CompetencyController {

    private final CompetencyService competencyService;

    public CompetencyController(CompetencyService competencyService) {
        this.competencyService = competencyService;
    }

    @PostMapping
    public CompetencyResponseDTO create(@Valid @RequestBody CompetencyRequestDTO request) {
        return competencyService.create(request);
    }

    @GetMapping
    public List<CompetencyResponseDTO> getAll() {
        return competencyService.getAll();
    }

    @GetMapping("/{id}")
    public CompetencyResponseDTO getById(@PathVariable UUID id) {
        return competencyService.getById(id);
    }

    @PutMapping("/{id}")
    public CompetencyResponseDTO update(@PathVariable UUID id, @RequestBody CompetencyRequestDTO request) {
        return competencyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        competencyService.delete(id);
    }

    @PostMapping("/frameworks")
    public CompetencyFrameworkResponseDTO defineFramework(@RequestBody CompetencyFrameworkRequestDTO request) {
        return competencyService.defineFrameworkRequirement(request);
    }

    @GetMapping("/frameworks/role/{role}")
    public List<CompetencyFrameworkResponseDTO> getFrameworkByRole(@PathVariable String role) {
        return competencyService.getFrameworkForRole(role);
    }

    @PostMapping("/employee-levels")
    public EmployeeCompetencyResponseDTO recordEmployeeLevel(@RequestBody EmployeeCompetencyRequestDTO request) {
        return competencyService.recordEmployeeLevel(request);
    }

    @GetMapping("/gap-analysis")
    public List<GapResult> getGapAnalysis(@RequestParam UUID employeeId, @RequestParam String role) {
        return competencyService.analyzeGap(employeeId, role);
    }
}
