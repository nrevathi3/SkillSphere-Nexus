package com.skillsphere.service;

import com.skillsphere.entity.Employee;
import com.skillsphere.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID empId) {
        return employeeRepository.findById(empId).orElse(null);
    }

    public void deleteEmployee(UUID empId) {
        employeeRepository.deleteById(empId);
    }
}