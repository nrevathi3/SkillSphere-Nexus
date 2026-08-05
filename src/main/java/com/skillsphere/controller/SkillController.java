package com.skillsphere.controller;

import com.skillsphere.entity.Skill;
import com.skillsphere.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping
    public Skill saveSkill(@RequestBody Skill skill) {
        return skillService.saveSkill(skill);
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable UUID id) {
        return skillService.getSkillById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable UUID id) {
        skillService.deleteSkill(id);
        return "Skill deleted successfully";
    }
}