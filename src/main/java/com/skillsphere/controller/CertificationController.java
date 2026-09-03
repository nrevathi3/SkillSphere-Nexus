package com.skillsphere.controller;

import com.skillsphere.entity.Certification;
import com.skillsphere.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    // Create certification
    @PostMapping
    public ResponseEntity<Certification> createCertification(
            @RequestBody Certification certification) {

        return ResponseEntity.ok(
                certificationService.createCertification(certification)
        );
    }

    // Get all certifications
    @GetMapping
    public ResponseEntity<List<Certification>> getAllCertifications() {

        return ResponseEntity.ok(
                certificationService.getAllCertifications()
        );
    }

    // Get certifications by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Certification>> getByEmployee(
            @PathVariable UUID employeeId) {

        return ResponseEntity.ok(
                certificationService.getCertificationsByEmployee(employeeId)
        );
    }

    // Get certification by ID
    @GetMapping("/{certificationId}")
    public ResponseEntity<Certification> getById(
            @PathVariable UUID certificationId) {

        return ResponseEntity.ok(
                certificationService.getCertificationById(certificationId)
        );
    }

    // Update certification
    @PutMapping("/{certificationId}")
    public ResponseEntity<Certification> updateCertification(
            @PathVariable UUID certificationId,
            @RequestBody Certification certification) {

        return ResponseEntity.ok(
                certificationService.updateCertification(
                        certificationId,
                        certification
                )
        );
    }

    // Delete certification
    @DeleteMapping("/{certificationId}")
    public ResponseEntity<String> deleteCertification(
            @PathVariable UUID certificationId) {

        certificationService.deleteCertification(certificationId);

        return ResponseEntity.ok(
                "Certification deleted successfully"
        );
    }

    // Get expired certifications
    @GetMapping("/expired")
    public ResponseEntity<List<Certification>> getExpiredCertifications() {

        return ResponseEntity.ok(
                certificationService.getExpiredCertifications()
        );
    }

    // Get certifications expiring within 30 days
    @GetMapping("/expiring")
    public ResponseEntity<List<Certification>> getExpiringCertifications() {

        return ResponseEntity.ok(
                certificationService.getExpiringCertifications()
        );
    }
}