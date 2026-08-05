package com.skillsphere.controller;

import com.skillsphere.dto.CompetencyFrameworkDTO;
import com.skillsphere.service.CompetencyFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/competency-frameworks")
public class CompetencyFrameworkController {

    @Autowired
    private CompetencyFrameworkService competencyFrameworkService;

    @PostMapping
    public CompetencyFrameworkDTO addCompetencyFramework(@RequestBody CompetencyFrameworkDTO competencyFrameworkDTO) {
        return competencyFrameworkService.addCompetencyFramework(competencyFrameworkDTO);
    }

    @GetMapping
    public List<CompetencyFrameworkDTO> getAllCompetencyFrameworks() {
        return competencyFrameworkService.getAllCompetencyFrameworks();
    }

    @GetMapping("/{id}")
    public CompetencyFrameworkDTO getCompetencyFrameworkById(@PathVariable UUID id) {
        return competencyFrameworkService.getCompetencyFrameworkById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCompetencyFramework(@PathVariable UUID id) {
        competencyFrameworkService.deleteCompetencyFramework(id);
        return "Competency Framework deleted successfully";
    }
}