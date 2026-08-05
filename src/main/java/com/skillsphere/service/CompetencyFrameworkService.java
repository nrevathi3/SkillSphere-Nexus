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

    public CompetencyFrameworkDTO addCompetencyFramework(CompetencyFrameworkDTO competencyFrameworkDTO) {

        CompetencyFramework competencyFramework = CompetencyFramework.builder()
                .competencyName(competencyFrameworkDTO.getCompetencyName())
                .description(competencyFrameworkDTO.getDescription())
                .proficiencyLevel(competencyFrameworkDTO.getProficiencyLevel())
                .category(competencyFrameworkDTO.getCategory())
                .build();

        CompetencyFramework savedCompetency = competencyFrameworkRepository.save(competencyFramework);

        return CompetencyFrameworkDTO.builder()
                .competencyFrameworkId(savedCompetency.getCompetencyFrameworkId())
                .competencyName(savedCompetency.getCompetencyName())
                .description(savedCompetency.getDescription())
                .proficiencyLevel(savedCompetency.getProficiencyLevel())
                .category(savedCompetency.getCategory())
                .build();
    }


    public List<CompetencyFrameworkDTO> getAllCompetencyFrameworks() {

        return competencyFrameworkRepository.findAll()
                .stream()
                .map(competency -> CompetencyFrameworkDTO.builder()
                        .competencyFrameworkId(competency.getCompetencyFrameworkId())
                        .competencyName(competency.getCompetencyName())
                        .description(competency.getDescription())
                        .proficiencyLevel(competency.getProficiencyLevel())
                        .category(competency.getCategory())
                        .build())
                .collect(Collectors.toList());
    }


    public CompetencyFrameworkDTO getCompetencyFrameworkById(UUID id) {

        CompetencyFramework competency = competencyFrameworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competency Framework not found"));

        return CompetencyFrameworkDTO.builder()
                .competencyFrameworkId(competency.getCompetencyFrameworkId())
                .competencyName(competency.getCompetencyName())
                .description(competency.getDescription())
                .proficiencyLevel(competency.getProficiencyLevel())
                .category(competency.getCategory())
                .build();
    }

    public void deleteCompetencyFramework(UUID id) {
        competencyFrameworkRepository.deleteById(id);
    }
}