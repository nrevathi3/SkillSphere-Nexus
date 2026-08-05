package com.skillsphere.service;

import com.skillsphere.entity.EmployeeSkill;
import com.skillsphere.repository.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeSkillService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    public EmployeeSkill saveEmployeeSkill(EmployeeSkill employeeSkill) {
        return employeeSkillRepository.save(employeeSkill);
    }

    public List<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillRepository.findAll();
    }

    public EmployeeSkill getEmployeeSkillById(UUID employeeSkillId) {
        return employeeSkillRepository.findById(employeeSkillId).orElse(null);
    }

    public void deleteEmployeeSkill(UUID employeeSkillId) {
        employeeSkillRepository.deleteById(employeeSkillId);
    }
}