package com.skillsphere.service;

import com.skillsphere.dto.CertificationReportDTO;
import com.skillsphere.entity.Certification;
import com.skillsphere.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationReportService {

    private final CertificationRepository certificationRepository;

    public CertificationReportDTO generateReport() {

        List<Certification> certifications =
                certificationRepository.findAll();

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

        double renewalRate = 0.0;

        if (total > 0) {
            renewalRate =
                    ((double) valid / total) * 100;
        }

        return CertificationReportDTO.builder()
                .totalCertifications(total)
                .validCertifications(valid)
                .expiredCertifications(expired)
                .expiringSoon(expiringSoon)
                .renewalRate(renewalRate)
                .build();
    }
}