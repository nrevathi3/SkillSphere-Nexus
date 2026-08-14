package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillProfileDTO {

    private UUID empId;
    private EmployeeDTO employee;
    private List<EmployeeSkillDTO> skills;
    private List<CertificationDTO> certifications;
    private List<AssessmentDTO> assessments;
}