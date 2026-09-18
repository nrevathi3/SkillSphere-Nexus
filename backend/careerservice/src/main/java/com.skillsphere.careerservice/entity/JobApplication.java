package com.skillsphere.careerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID applicationId;

    @Column(nullable = false)
    private UUID jobId;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private UUID employeeId;

    @Column(name = "current_role_name")
    private String currentRole;

    private Integer yearsOfExperience;

    @Column(columnDefinition = "TEXT")
    private String relevantSkills;

    @Column(columnDefinition = "TEXT")
    private String interestMessage;

    private String resumeFileName;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public enum ApplicationStatus {
        APPLIED,
        UNDER_REVIEW,
        SHORTLISTED,
        INTERVIEW,
        SELECTED,
        REJECTED
    }
}