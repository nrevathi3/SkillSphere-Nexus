package com.skillsphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "assessment")
public class Assessment {

    @Id
    @GeneratedValue
    private UUID assessmentId;

    @Column(nullable = false)
    private String assessmentName;

    @Column(nullable = false)
    private UUID employeeId;

    @Column(nullable = false)
    private UUID skillId;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private LocalDate assessmentDate;

    @Column(nullable = false)
    private String result;
}