package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import com.skillspherenexus.skillmanagementservice.entity.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer employeeId);
    Employee updateEmployee(Integer employeeId, Employee employee);
    void deleteEmployee(Integer employeeId);
}