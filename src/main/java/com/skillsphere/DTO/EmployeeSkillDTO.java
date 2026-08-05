package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkillDTO {

    private UUID employeeSkillId;
    private UUID employeeId;
    private UUID skillId;
    private Integer skillLevel;
    private Integer yearsOfExperience;
    private Boolean verified;
}