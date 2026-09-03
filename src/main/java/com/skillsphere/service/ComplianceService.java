package com.skillsphere.service;

import com.skillsphere.dto.ComplianceDTO;
import com.skillsphere.entity.Certification;
import com.skillsphere.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComplianceService {

    private final CertificationRepository certificationRepository;

    public ComplianceDTO getEmployeeCompliance(UUID employeeId) {

        List<Certification> certifications =
                certificationRepository.findByEmployeeId(employeeId);

        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        int total = certifications.size();
        int valid = 0;
        int expired = 0;
        int expiringSoon = 0;

        for (Certification certification : certifications) {

            LocalDate expiryDate = certification.getExpiryDate();

            if (expiryDate.isBefore(today)) {
                expired++;

            } else if (!expiryDate.isAfter(thirtyDaysLater)) {
                expiringSoon++;

            } else {
                valid++;
            }
        }

        boolean compliant = expired == 0;

        return ComplianceDTO.builder()
                .employeeId(employeeId)
                .totalCertifications(total)
                .validCertifications(valid)
                .expiredCertifications(expired)
                .expiringSoon(expiringSoon)
                .compliant(compliant)
                .build();
    }
}