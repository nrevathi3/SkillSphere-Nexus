package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO {

    private UUID assessmentId;
    private UUID employeeId;
    private UUID skillId;
    private String assessmentName;
    private Integer score;
    private LocalDate assessmentDate;
    private String result;
}