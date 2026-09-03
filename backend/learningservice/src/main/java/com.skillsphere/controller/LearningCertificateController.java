package com.skillsphere.controller;

import com.skillsphere.entity.LearningCertificate;
import com.skillsphere.service.LearningCertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class LearningCertificateController {

    private final LearningCertificateService certificateService;

    @PostMapping("/{enrollmentId}")
    public LearningCertificate generateCertificate(
            @PathVariable UUID enrollmentId) {

        return certificateService.generateCertificate(enrollmentId);
    }
}