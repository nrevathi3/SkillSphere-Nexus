package com.skillsphere.service;

import com.skillsphere.dto.SkillDTO;
import com.skillsphere.entity.Skill;
import com.skillsphere.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillCatalogService {

    private final SkillRepository skillRepository;

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO addSkill(SkillDTO dto) {

        Skill skill = Skill.builder()
                .skillName(dto.getSkillName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .competencyLevel(dto.getCompetencyLevel())
                .verified(dto.getVerified())
                .build();

        return toDTO(skillRepository.save(skill));
    }

    private SkillDTO toDTO(Skill skill) {

        return SkillDTO.builder()
                .skillId(skill.getSkillId())
                .skillName(skill.getSkillName())
                .description(skill.getDescription())
                .category(skill.getCategory())
                .competencyLevel(skill.getCompetencyLevel())
                .verified(skill.getVerified())
                .build();
    }
}