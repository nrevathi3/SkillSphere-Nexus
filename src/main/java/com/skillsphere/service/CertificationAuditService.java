package com.skillsphere.service;

import com.skillsphere.entity.CertificationAudit;
import com.skillsphere.repository.CertificationAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificationAuditService {

    private final CertificationAuditRepository auditRepository;

    public CertificationAudit saveAudit(CertificationAudit audit) {
        return auditRepository.save(audit);
    }

    public List<CertificationAudit> getAuditHistory(UUID certificationId) {
        return auditRepository.findByCertificationId(certificationId);
    }
}