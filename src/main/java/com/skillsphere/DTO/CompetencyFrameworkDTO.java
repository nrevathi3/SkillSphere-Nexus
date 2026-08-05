package com.skillsphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetencyFrameworkDTO {

    private UUID competencyFrameworkId;
    private String competencyName;
    private String description;
    private String proficiencyLevel;
    private String category;
}