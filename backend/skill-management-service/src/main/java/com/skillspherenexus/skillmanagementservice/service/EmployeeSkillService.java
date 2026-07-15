package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.entity.EmployeeSkill;

public interface EmployeeSkillService {

    EmployeeSkill saveEmployeeSkill(EmployeeSkill employeeSkill);

    List<EmployeeSkill> getAllEmployeeSkills();

    EmployeeSkill getEmployeeSkillById(Integer employeeSkillId);

    EmployeeSkill updateEmployeeSkill(Integer employeeSkillId, EmployeeSkill employeeSkill);

    void deleteEmployeeSkill(Integer employeeSkillId);
}