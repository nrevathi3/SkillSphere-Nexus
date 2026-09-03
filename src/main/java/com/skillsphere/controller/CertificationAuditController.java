package com.skillsphere.controller;

import com.skillsphere.entity.CertificationAudit;
import com.skillsphere.service.CertificationAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certifications/audit")
@RequiredArgsConstructor
public class CertificationAuditController {

    private final CertificationAuditService auditService;

    @PostMapping
    public CertificationAudit saveAudit(
            @RequestBody CertificationAudit audit) {

        return auditService.saveAudit(audit);
    }

    @GetMapping("/{certificationId}")
    public List<CertificationAudit> getAuditHistory(
            @PathVariable UUID certificationId) {

        return auditService.getAuditHistory(certificationId);
    }
}