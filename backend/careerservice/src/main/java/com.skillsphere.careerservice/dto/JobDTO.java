package com.skillsphere.careerservice.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {

    private UUID jobId;

    private String title;

    private String department;

    private String requiredSkills;

    private Integer minimumExperience;

    private Boolean active;
}