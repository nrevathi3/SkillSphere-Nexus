package com.skillsphere.service;

import com.skillsphere.entity.Skill;
import com.skillsphere.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(UUID skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }

    public void deleteSkill(UUID skillId) {
        skillRepository.deleteById(skillId);
    }
}