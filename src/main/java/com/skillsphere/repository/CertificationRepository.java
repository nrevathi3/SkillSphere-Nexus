package com.skillsphere.repository;

import com.skillsphere.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    List<Certification> findByEmployeeId(UUID employeeId);
}