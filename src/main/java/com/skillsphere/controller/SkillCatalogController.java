package com.skillsphere.controller;

import com.skillsphere.dto.SkillDTO;
import com.skillsphere.service.SkillCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills/catalog")
@RequiredArgsConstructor
public class SkillCatalogController {

    private final SkillCatalogService skillCatalogService;

    @GetMapping
    public List<SkillDTO> getAllSkills() {
        return skillCatalogService.getAllSkills();
    }

    @PostMapping
    @PreAuthorize("hasRole('HR')")
    public SkillDTO addSkill(@RequestBody SkillDTO skillDTO) {
        return skillCatalogService.addSkill(skillDTO);
    }
}