package com.skillsphere.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "competency_framework")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyFramework {

    @Id
    @GeneratedValue
    private UUID competencyFrameworkId;

    @Column(nullable = false)
    private String competencyName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String proficiencyLevel;

    @Column(nullable = false)
    private String category;
}