package com.skillsphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID certificationId;

    @Column(nullable = false)
    private UUID employeeId;

    @Column(nullable = false)
    private String certificationName;

    @Column(nullable = false)
    private String organization;

    private String credentialId;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String status;
}