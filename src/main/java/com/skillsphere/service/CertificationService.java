package com.skillsphere.service;

import com.skillsphere.entity.Certification;
import com.skillsphere.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public Certification saveCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    public List<Certification> getAllCertifications() {
        return certificationRepository.findAll();
    }

    public Certification getCertificationById(UUID certificationId) {
        return certificationRepository.findById(certificationId).orElse(null);
    }

    public void deleteCertification(UUID certificationId) {
        certificationRepository.deleteById(certificationId);
    }
}