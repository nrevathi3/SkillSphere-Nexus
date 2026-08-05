package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationDTO {

    private UUID certificationId;
    private UUID employeeId;
    private String certificationName;
    private String organization;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String status;
}