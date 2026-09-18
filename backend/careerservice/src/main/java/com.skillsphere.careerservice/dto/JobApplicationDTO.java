package com.skillsphere.careerservice.dto;

import com.skillsphere.careerservice.entity.JobApplication.ApplicationStatus;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplicationDTO {

    private UUID applicationId;

    private UUID jobId;

    private String jobTitle;

    private String employeeName;

    private UUID employeeId;

    private String currentRole;

    private Integer yearsOfExperience;

    private String relevantSkills;

    private String interestMessage;

    private String resumeFileName;

    private ApplicationStatus status;
}