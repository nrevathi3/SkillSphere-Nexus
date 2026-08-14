package com.skillsphere.controller;

import com.skillsphere.dto.SkillProfileDTO;
import com.skillsphere.service.SkillProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillProfileController {

    private final SkillProfileService skillProfileService;

    @GetMapping("/employee/{empId}")
    public SkillProfileDTO getProfile(@PathVariable UUID empId) {
        return skillProfileService.getSkillProfile(empId);
    }
}