package com.skillsphere.repository;

import com.skillsphere.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {

}