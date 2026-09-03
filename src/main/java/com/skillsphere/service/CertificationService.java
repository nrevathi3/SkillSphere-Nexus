package com.skillsphere.service;

import com.skillsphere.entity.Certification;
import com.skillsphere.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;

    public Certification createCertification(Certification certification) {

        updateStatus(certification);

        return certificationRepository.save(certification);
    }

    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    public List<Certification> getCertificationsByEmployee(UUID employeeId) {
        return certificationRepository.findByEmployeeId(employeeId);
    }

    public Certification getCertificationById(UUID certificationId) {

        return certificationRepository.findById(certificationId)
                .orElseThrow(() ->
                        new RuntimeException("Certification not found"));
    }

    public Certification updateCertification(
            UUID certificationId,
            Certification updatedCertification) {

        Certification existing = getCertificationById(certificationId);

        existing.setEmployeeId(updatedCertification.getEmployeeId());
        existing.setCertificationName(
                updatedCertification.getCertificationName()
        );
        existing.setOrganization(
                updatedCertification.getOrganization()
        );
        existing.setCredentialId(
                updatedCertification.getCredentialId()
        );
        existing.setIssueDate(
                updatedCertification.getIssueDate()
        );
        existing.setExpiryDate(
                updatedCertification.getExpiryDate()
        );

        updateStatus(existing);

        return certificationRepository.save(existing);
    }

    public void deleteCertification(UUID certificationId) {

        Certification certification =
                getCertificationById(certificationId);

        certificationRepository.delete(certification);
    }

    public List<Certification> getExpiredCertifications() {

        return certificationRepository.findByExpiryDateBefore(
                LocalDate.now()
        );
    }

    public List<Certification> getExpiringCertifications() {

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        return certificationRepository.findByExpiryDateBetween(
                today,
                thirtyDaysLater
        );
    }

    private void updateStatus(Certification certification) {

        LocalDate today = LocalDate.now();

        if (certification.getExpiryDate().isBefore(today)) {

            certification.setStatus("EXPIRED");

        } else if (!certification.getExpiryDate()
                .isAfter(today.plusDays(30))) {

            certification.setStatus("PENDING_RENEWAL");

        } else {

            certification.setStatus("VALID");
        }
    }
}