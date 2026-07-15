package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.dto.GapResult;
import com.skillspherenexus.skillmanagementservice.entity.Competency;
import com.skillspherenexus.skillmanagementservice.entity.CompetencyFramework;
import com.skillspherenexus.skillmanagementservice.entity.EmployeeCompetency;
import com.skillspherenexus.skillmanagementservice.repository.CompetencyFrameworkRepository;
import com.skillspherenexus.skillmanagementservice.repository.CompetencyRepository;
import com.skillspherenexus.skillmanagementservice.repository.EmployeeCompetencyRepository;

@Service
public class CompetencyServiceImpl implements CompetencyService {

    private final CompetencyRepository competencyRepository;
    private final CompetencyFrameworkRepository frameworkRepository;
    private final EmployeeCompetencyRepository employeeCompetencyRepository;

    public CompetencyServiceImpl(CompetencyRepository competencyRepository,
                                 CompetencyFrameworkRepository frameworkRepository,
                                 EmployeeCompetencyRepository employeeCompetencyRepository) {
        this.competencyRepository = competencyRepository;
        this.frameworkRepository = frameworkRepository;
        this.employeeCompetencyRepository = employeeCompetencyRepository;
    }

    // ---------------- Competency ----------------

    @Override
    public Competency create(Competency competency) {
        return competencyRepository.save(competency);
    }

    @Override
    public List<Competency> getAll() {
        return competencyRepository.findAll();
    }

    @Override
    public Competency getById(UUID id) {
        return competencyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competency not found: " + id));
    }

    @Override
    public Competency update(UUID id, Competency updatedData) {

        Competency existing = getById(id);

        existing.setName(updatedData.getName());
        existing.setCategory(updatedData.getCategory());
        existing.setDescription(updatedData.getDescription());
        existing.setMaxLevel(updatedData.getMaxLevel());

        return competencyRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        Competency existing = getById(id);
        competencyRepository.delete(existing);
    }

    // ---------------- Competency Framework ----------------

    @Override
    public CompetencyFramework defineFrameworkRequirement(CompetencyFramework framework) {
        return frameworkRepository.save(framework);
    }

    @Override
    public List<CompetencyFramework> getFrameworkForRole(String role) {
        return frameworkRepository.findByRole(role);
    }

    // ---------------- Employee Competency ----------------

    @Override
    public EmployeeCompetency recordEmployeeLevel(EmployeeCompetency input) {

        UUID employeeId = input.getEmployeeId();
        UUID competencyId = input.getCompetency().getCompetencyId();

        EmployeeCompetency existing = employeeCompetencyRepository
                .findByEmployeeIdAndCompetency_CompetencyId(employeeId, competencyId)
                .orElse(new EmployeeCompetency());

        existing.setEmployeeId(employeeId);
        existing.setCompetency(getById(competencyId));
        existing.setCurrentLevel(input.getCurrentLevel());

        return employeeCompetencyRepository.save(existing);
    }

    // ---------------- Gap Analysis ----------------

    @Override
    public List<GapResult> analyzeGap(UUID employeeId, String targetRole) {

        List<CompetencyFramework> requirements =
                frameworkRepository.findByRole(targetRole);

        return requirements.stream()
                .map(req -> {

                    UUID competencyId = req.getCompetency().getCompetencyId();

                    int currentLevel = employeeCompetencyRepository
                            .findByEmployeeIdAndCompetency_CompetencyId(employeeId, competencyId)
                            .map(EmployeeCompetency::getCurrentLevel)
                            .orElse(0);

                    int gap = req.getRequiredLevel() - currentLevel;

                    return new GapResult(
                            req.getCompetency().getName(),
                            req.getRequiredLevel(),
                            currentLevel,
                            gap
                    );
                })
                .toList();
    }
}