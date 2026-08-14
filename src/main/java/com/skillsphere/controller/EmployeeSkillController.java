package com.skillsphere.controller;

import com.skillsphere.entity.EmployeeSkill;
import com.skillsphere.service.EmployeeSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee-skills")
public class EmployeeSkillController {

    @Autowired
    private EmployeeSkillService employeeSkillService;

    @PostMapping
    public EmployeeSkill saveEmployeeSkill(
            @RequestBody EmployeeSkill employeeSkill) {
        return employeeSkillService.saveEmployeeSkill(employeeSkill);
    }

    @GetMapping
    public List<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillService.getAllEmployeeSkills();
    }

    @GetMapping("/{id}")
    public EmployeeSkill getEmployeeSkillById(
            @PathVariable UUID id) {
        return employeeSkillService.getEmployeeSkillById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeSkill(
            @PathVariable UUID id) {
        employeeSkillService.deleteEmployeeSkill(id);
        return "Employee skill deleted successfully";
    }
}