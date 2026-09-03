package com.skillsphere.repository;

import com.skillsphere.entity.LearningCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LearningCertificateRepository
        extends JpaRepository<LearningCertificate, UUID> {
}