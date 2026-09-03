package com.skillsphere.controller;

import com.skillsphere.dto.CertificationReportDTO;
import com.skillsphere.service.CertificationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certifications/report")
@RequiredArgsConstructor
public class CertificationReportController {

    private final CertificationReportService certificationReportService;

    @GetMapping
    public CertificationReportDTO generateReport() {

        return certificationReportService.generateReport();
    }
}