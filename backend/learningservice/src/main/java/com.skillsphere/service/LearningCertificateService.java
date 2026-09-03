package com.skillsphere.service;

import com.skillsphere.entity.Enrollment;
import com.skillsphere.entity.LearningCertificate;
import com.skillsphere.repository.EnrollmentRepository;
import com.skillsphere.repository.LearningCertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LearningCertificateService {

    private final EnrollmentRepository enrollmentRepository;
    private final LearningCertificateRepository certificateRepository;

    public LearningCertificate generateCertificate(UUID enrollmentId) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() ->
                        new RuntimeException("Enrollment not found"));

        if (!Boolean.TRUE.equals(enrollment.getCompleted())) {
            throw new RuntimeException(
                    "Course is not completed. Certificate cannot be generated."
            );
        }

        LearningCertificate certificate = LearningCertificate.builder()
                .empId(enrollment.getEmpId())
                .courseId(enrollment.getCourse().getCourseId())
                .courseName(enrollment.getCourse().getTitle())
                .score(enrollment.getScore())
                .issuedDate(LocalDate.now())
                .certificateNumber("CERT-" + UUID.randomUUID())
                .build();

        return certificateRepository.save(certificate);
    }
}