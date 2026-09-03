package com.skillsphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "certification_audit")
public class CertificationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID auditId;

    @Column(nullable = false)
    private UUID certificationId;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String performedBy;

    @Column(nullable = false)
    private LocalDateTime performedAt;

    private String details;
}