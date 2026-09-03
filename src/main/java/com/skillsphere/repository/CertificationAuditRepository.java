package com.skillsphere.repository;

import com.skillsphere.entity.CertificationAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificationAuditRepository
        extends JpaRepository<CertificationAudit, UUID> {

    List<CertificationAudit> findByCertificationId(UUID certificationId);
}