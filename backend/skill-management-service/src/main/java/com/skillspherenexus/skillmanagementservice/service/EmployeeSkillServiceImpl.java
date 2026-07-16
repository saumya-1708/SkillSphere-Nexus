package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.dto.EmployeeSkillRequestDTO;
import com.skillspherenexus.skillmanagementservice.dto.EmployeeSkillResponseDTO;
import com.skillspherenexus.skillmanagementservice.entity.EmployeeSkill;
import com.skillspherenexus.skillmanagementservice.repository.EmployeeSkillRepository;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    @Override
    public EmployeeSkillResponseDTO saveEmployeeSkill(EmployeeSkillRequestDTO request) {
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmployeeId(request.getEmployeeId());
        employeeSkill.setSkillId(request.getSkillId());
        employeeSkill.setProficiencyLevel(request.getProficiencyLevel());
        employeeSkill.setYearsOfExperience(request.getYearsOfExperience());

        return convertToResponse(employeeSkillRepository.save(employeeSkill));
    }

    @Override
    public List<EmployeeSkillResponseDTO> getAllEmployeeSkills() {
        return employeeSkillRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeSkillResponseDTO getEmployeeSkillById(Integer employeeSkillId) {
        return employeeSkillRepository.findById(employeeSkillId)
                .map(this::convertToResponse)
                .orElse(null);
    }

    @Override
    public EmployeeSkillResponseDTO updateEmployeeSkill(Integer employeeSkillId, EmployeeSkillRequestDTO request) {

        EmployeeSkill existingEmployeeSkill = employeeSkillRepository
                .findById(employeeSkillId)
                .orElseThrow(() -> new RuntimeException("Employee Skill not found"));

        existingEmployeeSkill.setEmployeeId(request.getEmployeeId());
        existingEmployeeSkill.setSkillId(request.getSkillId());
        existingEmployeeSkill.setProficiencyLevel(request.getProficiencyLevel());
        existingEmployeeSkill.setYearsOfExperience(request.getYearsOfExperience());

        return convertToResponse(employeeSkillRepository.save(existingEmployeeSkill));
    }

    @Override
    public void deleteEmployeeSkill(Integer employeeSkillId) {
        employeeSkillRepository.deleteById(employeeSkillId);
    }

    private EmployeeSkillResponseDTO convertToResponse(EmployeeSkill employeeSkill) {
        EmployeeSkillResponseDTO response = new EmployeeSkillResponseDTO();
        response.setEmployeeSkillId(employeeSkill.getEmployeeSkillId());
        response.setEmployeeId(employeeSkill.getEmployeeId());
        response.setSkillId(employeeSkill.getSkillId());
        response.setProficiencyLevel(employeeSkill.getProficiencyLevel());
        response.setYearsOfExperience(employeeSkill.getYearsOfExperience());
        return response;
    }
}