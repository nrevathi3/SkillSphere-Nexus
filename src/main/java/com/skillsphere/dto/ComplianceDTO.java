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
public class ComplianceDTO {

    private UUID employeeId;

    private String employeeName;

    private int totalCertifications;

    private int validCertifications;

    private int expiredCertifications;

    private int expiringSoon;

    private boolean compliant;
}