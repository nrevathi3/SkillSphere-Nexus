package com.skillsphere.service;

import com.skillsphere.dto.*;
import com.skillsphere.entity.*;
import com.skillsphere.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillProfileService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    private final AssessmentRepository assessmentRepository;
    private final CertificationRepository certificationRepository;

    public SkillProfileDTO getSkillProfile(UUID empId) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + empId));

        List<EmployeeSkillDTO> skills =
                employeeSkillRepository.findByEmployeeId(empId)
                        .stream()
                        .map(skill -> EmployeeSkillDTO.builder()
                                .employeeSkillId(skill.getEmployeeSkillId())
                                .employeeId(skill.getEmployeeId())
                                .skillId(skill.getSkillId())
                                .skillLevel(skill.getSkillLevel())
                                .yearsOfExperience(skill.getYearsOfExperience())
                                .verified(skill.getVerified())
                                .build())
                        .collect(Collectors.toList());

        List<CertificationDTO> certifications =
                certificationRepository.findByEmployeeId(empId)
                        .stream()
                        .map(certification -> CertificationDTO.builder()
                                .certificationId(certification.getCertificationId())
                                .employeeId(certification.getEmployeeId())
                                .certificationName(certification.getCertificationName())
                                .organization(certification.getOrganization())
                                .issueDate(certification.getIssueDate())
                                .expiryDate(certification.getExpiryDate())
                                .status(certification.getStatus())
                                .build())
                        .collect(Collectors.toList());

        List<AssessmentDTO> assessments =
                assessmentRepository.findByEmployeeId(empId)
                        .stream()
                        .map(assessment -> AssessmentDTO.builder()
                                .assessmentId(assessment.getAssessmentId())
                                .employeeId(assessment.getEmployeeId())
                                .skillId(assessment.getSkillId())
                                .assessmentName(assessment.getAssessmentName())
                                .score(assessment.getScore())
                                .assessmentDate(assessment.getAssessmentDate())
                                .result(assessment.getResult())
                                .passed(assessment.getPassed())
                                .verified(assessment.getVerified())
                                .build())
                        .collect(Collectors.toList());

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .employeeCode(employee.getEmployeeCode())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .experience(employee.getExperience())
                .status(employee.getStatus())
                .build();

        return SkillProfileDTO.builder()
                .empId(empId)
                .employee(employeeDTO)
                .skills(skills)
                .certifications(certifications)
                .assessments(assessments)
                .build();
    }
}