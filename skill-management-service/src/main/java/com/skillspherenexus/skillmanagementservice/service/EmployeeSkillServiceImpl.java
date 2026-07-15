package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.EmployeeSkill;
import com.skillspherenexus.skillmanagementservice.repository.EmployeeSkillRepository;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    @Override
    public EmployeeSkill saveEmployeeSkill(EmployeeSkill employeeSkill) {
        return employeeSkillRepository.save(employeeSkill);
    }

    @Override
    public List<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillRepository.findAll();
    }

    @Override
    public EmployeeSkill getEmployeeSkillById(Integer employeeSkillId) {
        return employeeSkillRepository.findById(employeeSkillId).orElse(null);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Integer employeeSkillId, EmployeeSkill employeeSkill) {

        EmployeeSkill existingEmployeeSkill = employeeSkillRepository
                .findById(employeeSkillId)
                .orElseThrow(() -> new RuntimeException("Employee Skill not found"));

        existingEmployeeSkill.setEmployeeId(employeeSkill.getEmployeeId());
        existingEmployeeSkill.setSkillId(employeeSkill.getSkillId());
        existingEmployeeSkill.setProficiencyLevel(employeeSkill.getProficiencyLevel());
        existingEmployeeSkill.setYearsOfExperience(employeeSkill.getYearsOfExperience());

        return employeeSkillRepository.save(existingEmployeeSkill);
    }

    @Override
    public void deleteEmployeeSkill(Integer employeeSkillId) {
        employeeSkillRepository.deleteById(employeeSkillId);
    }
}