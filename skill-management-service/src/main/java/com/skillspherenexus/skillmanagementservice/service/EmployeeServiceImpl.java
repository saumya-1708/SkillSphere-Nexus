package com.skillspherenexus.skillmanagementservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillspherenexus.skillmanagementservice.entity.Employee;
import com.skillspherenexus.skillmanagementservice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public Employee updateEmployee(Integer employeeId, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);

        if (existingEmployee != null) {

            existingEmployee.setEmployeeName(employee.getEmployeeName());
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setSalary(employee.getSalary());

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }
    @Override
    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}