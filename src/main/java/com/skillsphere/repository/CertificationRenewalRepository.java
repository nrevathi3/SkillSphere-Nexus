package com.skillsphere.repository;

import com.skillsphere.entity.CertificationRenewal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificationRenewalRepository
        extends JpaRepository<CertificationRenewal, UUID> {

    List<CertificationRenewal> findByCertificationCertificationId(
            UUID certificationId
    );
}