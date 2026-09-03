package com.skillsphere.service;

import com.skillsphere.dto.RenewalDTO;
import com.skillsphere.entity.Certification;
import com.skillsphere.entity.CertificationRenewal;
import com.skillsphere.event.CertificationRenewedEvent;
import com.skillsphere.repository.CertificationRenewalRepository;
import com.skillsphere.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RenewalService {

    private final CertificationRepository certificationRepository;
    private final CertificationRenewalRepository renewalRepository;
    private final KafkaProducerService kafkaProducerService;

    public RenewalDTO requestRenewal(
            UUID certificationId,
            String requestedBy) {

        Certification certification =
                certificationRepository.findById(certificationId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Certification not found"));

        CertificationRenewal renewal =
                CertificationRenewal.builder()
                        .certification(certification)
                        .oldExpiry(certification.getExpiryDate())
                        .status(
                                CertificationRenewal.RenewalStatus.REQUESTED)
                        .requestedBy(requestedBy)
                        .requestedAt(LocalDateTime.now())
                        .build();

        CertificationRenewal saved =
                renewalRepository.save(renewal);

        return toDTO(saved);
    }

    public RenewalDTO approveRenewal(
            UUID renewalId,
            LocalDate newExpiry,
            String approvedBy) {

        CertificationRenewal renewal =
                renewalRepository.findById(renewalId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Renewal not found"));

        Certification certification =
                renewal.getCertification();

        LocalDate oldExpiry =
                certification.getExpiryDate();

        certification.setExpiryDate(newExpiry);
        certification.setStatus(calculateStatus(newExpiry));

        certificationRepository.save(certification);

        renewal.setNewExpiry(newExpiry);
        renewal.setApprovedBy(approvedBy);
        renewal.setApprovedAt(LocalDateTime.now());
        renewal.setStatus(
                CertificationRenewal.RenewalStatus.APPROVED);

        CertificationRenewal saved =
                renewalRepository.save(renewal);

        // Publish Kafka event after successful approval
        CertificationRenewedEvent event =
                CertificationRenewedEvent.builder()
                        .certificationId(
                                certification.getCertificationId())
                        .employeeId(
                                certification.getEmployeeId())
                        .oldExpiry(oldExpiry)
                        .newExpiry(newExpiry)
                        .renewedBy(approvedBy)
                        .build();

        kafkaProducerService.publishCertificationRenewed(event);

        return toDTO(saved);
    }

    private String calculateStatus(LocalDate expiryDate) {

        LocalDate today = LocalDate.now();

        if (expiryDate == null ||
                expiryDate.isBefore(today)) {

            return "EXPIRED";
        }

        if (!expiryDate.isAfter(today.plusDays(30))) {

            return "PENDING_RENEWAL";
        }

        return "VALID";
    }

    private RenewalDTO toDTO(
            CertificationRenewal renewal) {

        return RenewalDTO.builder()
                .renewalId(renewal.getRenewalId())
                .certificationId(
                        renewal.getCertification()
                                .getCertificationId())
                .oldExpiry(renewal.getOldExpiry())
                .newExpiry(renewal.getNewExpiry())
                .status(
                        renewal.getStatus().name())
                .requestedBy(
                        renewal.getRequestedBy())
                .approvedBy(
                        renewal.getApprovedBy())
                .build();
    }
    public RenewalDTO getRenewal(UUID renewalId) {

        CertificationRenewal renewal =
                renewalRepository.findById(renewalId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Renewal not found"
                                ));

        return toDTO(renewal);
    }
}