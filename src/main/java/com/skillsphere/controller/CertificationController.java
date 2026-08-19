package com.skillsphere.controller;

import com.skillsphere.entity.Certification;
import com.skillsphere.service.CertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping
    public Certification saveCertification(@RequestBody Certification certification) {
        return certificationService.saveCertification(certification);
    }

    @GetMapping
    public List<Certification> getAllCertifications() {
        return certificationService.getAllCertifications();
    }

    @GetMapping("/{id}")
    public Certification getCertificationById(@PathVariable UUID id) {
        return certificationService.getCertificationById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCertification(@PathVariable UUID id) {
        certificationService.deleteCertification(id);
        return "Certification deleted successfully";
    }
}