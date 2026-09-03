package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificationReportDTO {

    private int totalCertifications;

    private int validCertifications;

    private int expiredCertifications;

    private int expiringSoon;

    private double renewalRate;
}