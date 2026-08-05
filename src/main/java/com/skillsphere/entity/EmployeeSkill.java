package com.skillsphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "employee_skill")
public class EmployeeSkill {

    @Id
    @GeneratedValue
    private UUID employeeSkillId;

    @Column(nullable = false)
    private UUID employeeId;

    @Column(nullable = false)
    private UUID skillId;

    @Column(nullable = false)
    private Integer skillLevel;

    @Column(nullable = false)
    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Boolean verified;
}