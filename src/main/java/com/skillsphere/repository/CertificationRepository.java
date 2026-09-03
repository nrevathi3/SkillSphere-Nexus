package com.skillsphere.repository;

import com.skillsphere.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {

    List<Certification> findByEmployeeId(UUID employeeId);

    List<Certification> findByExpiryDateBefore(LocalDate date);

    List<Certification> findByExpiryDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}