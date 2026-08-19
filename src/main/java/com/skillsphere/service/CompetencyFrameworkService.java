package com.skillsphere.service;

import com.skillsphere.dto.CompetencyFrameworkDTO;
import com.skillsphere.entity.CompetencyFramework;
import com.skillsphere.repository.CompetencyFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompetencyFrameworkService {

    @Autowired
    private CompetencyFrameworkRepository competencyFrameworkRepository;

    public CompetencyFrameworkDTO addCompetencyFramework(
            CompetencyFrameworkDTO competencyFrameworkDTO) {

        CompetencyFramework competencyFramework = CompetencyFramework.builder()
                .competencyName(competencyFrameworkDTO.getCompetencyName())
                .description(competencyFrameworkDTO.getDescription())
                .proficiencyLevel(competencyFrameworkDTO.getProficiencyLevel())
                .category(competencyFrameworkDTO.getCategory())
                .roleTitle(competencyFrameworkDTO.getRoleTitle())
                .skillId(competencyFrameworkDTO.getSkillId())
                .requiredProficiency(competencyFrameworkDTO.getRequiredProficiency())
                .build();

        CompetencyFramework savedCompetency =
                competencyFrameworkRepository.save(competencyFramework);

        return toDTO(savedCompetency);
    }

    public List<CompetencyFrameworkDTO> getAllCompetencyFrameworks() {

        return competencyFrameworkRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CompetencyFrameworkDTO getCompetencyFrameworkById(UUID id) {

        CompetencyFramework competency =
                competencyFrameworkRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Competency Framework not found"));

        return toDTO(competency);
    }

    public void deleteCompetencyFramework(UUID id) {
        competencyFrameworkRepository.deleteById(id);
    }

    private CompetencyFrameworkDTO toDTO(
            CompetencyFramework competency) {

        return CompetencyFrameworkDTO.builder()
                .competencyFrameworkId(
                        competency.getCompetencyFrameworkId())
                .competencyName(competency.getCompetencyName())
                .description(competency.getDescription())
                .proficiencyLevel(competency.getProficiencyLevel())
                .category(competency.getCategory())
                .roleTitle(competency.getRoleTitle())
                .skillId(competency.getSkillId())
                .requiredProficiency(
                        competency.getRequiredProficiency())
                .build();
    }
}